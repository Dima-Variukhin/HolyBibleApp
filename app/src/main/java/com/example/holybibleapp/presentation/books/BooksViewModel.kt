package com.example.holybibleapp.presentation.books

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.example.holybibleapp.NavigationCommunication
import com.example.holybibleapp.R
import com.example.holybibleapp.core.ResourceProvider
import com.example.holybibleapp.core.Save
import com.example.holybibleapp.core.Show
import com.example.holybibleapp.domain.books.BooksDomainToUiMapper
import com.example.holybibleapp.domain.books.BooksInteractor
import com.example.holybibleapp.presentation.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BooksViewModel(
    private val booksInteractor: BooksInteractor,
    private val mapper: BooksDomainToUiMapper<BooksUi>,
    private val communication: BooksCommunication,
    private val uiDataCache: UiDataCache,
    private val bookIdCache: Save<Int>,
    private val navigator: BooksNavigator,
    private val navigationCommunication: NavigationCommunication,
    resourceProvider: ResourceProvider
) : BaseViewModel(resourceProvider), Show {

    override fun getTitleResId() = R.string.app_name

    //проверка пересоздания инстанса вьюмодельки
    init {
        Log.d("jsc91", "booksViewModel()${hashCode()}")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("jsc91", "booksViewModel cleared()")
    }

    fun fetchBooks() {
        communication.map(BooksUi.Base(listOf(BookUi.Progress)))
        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = booksInteractor.fetchBooks()
            val resultUi = resultDomain.map(mapper)
            withContext(Dispatchers.Main) {
                communication.map(resultUi)
            }
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<BooksUi>) {
        communication.observe(owner, observer)
    }

    fun collapseOrExpand(id: Int) {
        communication.map(BooksUi.Base(uiDataCache.changeState(id)))
    }

    override fun open(id: Int) {
        bookIdCache.save(id)
        navigator.nextScreen(navigationCommunication)
    }

    fun init() {
        navigator.saveBooksScreen()
        fetchBooks()
    }

    fun save() = uiDataCache.saveState()

    override fun scrollPosition() = booksInteractor.scrollPosition()

    override fun saveScrollPosition(position: Int) = booksInteractor.saveScrollPosition(position)
}