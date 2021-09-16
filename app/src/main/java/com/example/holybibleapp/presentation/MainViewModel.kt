package com.example.holybibleapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.holybibleapp.domain.BooksDomainToUiMapper
import com.example.holybibleapp.domain.BooksInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val booksInteractor: BooksInteractor,
    private val mapper: BooksDomainToUiMapper,
    private val communication: BooksCommunication,
    private val uiDataCache: UiDataCache,
) : ViewModel() { //todo interface

    fun fetchBooks() {
        communication.map(listOf(BookUi.Progress))
        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = booksInteractor.fetchBooks()
            val resultUi = resultDomain.map(mapper)
            val actual = resultUi.cache(uiDataCache)
            withContext(Dispatchers.Main) {3
                actual.map(communication)
            }
        }
    }

    fun observer(owner: LifecycleOwner, observer: Observer<List<BookUi>>) {
        communication.observe(owner, observer)
    }

    fun collapseOrExpand(id: Int) {
        communication.map(uiDataCache.changeState(id))
    }

    fun saveCollapsedStates() {
        uiDataCache.saveState( )
    }
}