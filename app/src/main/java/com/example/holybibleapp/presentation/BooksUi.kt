package com.example.holybibleapp.presentation

import com.example.holybibleapp.core.Abstract

sealed class BooksUi : Abstract.Object<Unit, BooksCommunication> {
    abstract fun cache(uiDataCache: UiDataCache): BooksUi
    data class Base(private val books: List<BookUi>) : BooksUi() {
        // мапит к интерфейсу коммьюникейшена
        override fun map(mapper: BooksCommunication) = mapper.map(books)
        override fun cache(uiDataCache: UiDataCache) = uiDataCache.cache(books)
    }
}

