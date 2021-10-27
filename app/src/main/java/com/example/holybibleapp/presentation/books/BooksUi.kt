package com.example.holybibleapp.presentation.books

import com.example.holybibleapp.core.ListMapper


sealed class BooksUi {
    abstract fun map(mapper: ListMapper<BookUi>)
    data class Base(private val books: List<BookUi>) : BooksUi() {
        override fun map(mapper: ListMapper<BookUi>) = mapper.map(books)
    }
}

