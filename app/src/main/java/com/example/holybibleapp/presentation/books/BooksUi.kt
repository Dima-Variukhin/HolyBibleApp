package com.example.holybibleapp.presentation.books


sealed class BooksUi {
    abstract fun map(mapper: BooksCommunication)
    data class Base(private val books: List<BookUi>) : BooksUi() {
        override fun map(mapper: BooksCommunication) = mapper.map(books)
    }
}

