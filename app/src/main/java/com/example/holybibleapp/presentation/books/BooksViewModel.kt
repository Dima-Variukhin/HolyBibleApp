package com.example.holybibleapp.presentation.books

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.holybibleapp.NavigationCommunication
import com.example.holybibleapp.core.CacheDataSource
import com.example.holybibleapp.core.Save
import com.example.holybibleapp.domain.books.BooksDomainToUiMapper
import com.example.holybibleapp.domain.books.BooksInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BooksViewModel(
    private val booksInteractor: BooksInteractor,
    private val mapper: BooksDomainToUiMapper<BooksUi>,
    private val communication: BooksCommunication,
    private val uiDataCache: UiDataCache,
    private val bookIdCache: Save<Pair<Int, String>>,
    private val navigator: BooksNavigator,
    private val navigationCommunication: NavigationCommunication
) : ViewModel(), ShowBook { //todo interface

    init {
        Log.d("jsc91", "books newInstance")
    }

    fun fetchBooks() {
        communication.map(listOf(BookUi.Progress))
        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = booksInteractor.fetchBooks()
            val resultUi = resultDomain.map(mapper)
            withContext(Dispatchers.Main) {
                resultUi.map(communication)
            }
        }
    }

    fun observer(owner: LifecycleOwner, observer: Observer<List<BookUi>>) {
        communication.observe(owner, observer)
    }

    fun collapseOrExpand(id: Int) {
        communication.map(uiDataCache.changeState(id))
    }

    override fun show(id: Int, name: String) {
        bookIdCache.save(Pair(id, name))
        navigator.nextScreen(navigationCommunication)
    }

    fun init() {
        navigator.saveBooksScreen()
        fetchBooks()
    }

    fun save() = uiDataCache.saveState()

    override fun onCleared() {
        uiDataCache.saveState()
        Log.d("jsc91", "books cleared")
        super.onCleared()
    }
}