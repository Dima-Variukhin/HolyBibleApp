package com.example.holybibleapp.domain.books

import com.example.holybibleapp.data.books.BooksDataToDomainMapper
import com.example.holybibleapp.data.books.BooksRepository

interface BooksInteractor {

    suspend fun fetchBooks(): BooksDomain

    class Base(
        private val booksRepository: BooksRepository,
        private val mapper: BooksDataToDomainMapper<BooksDomain>
    ) : BooksInteractor {
        override suspend fun fetchBooks() = booksRepository.fetchData().map(mapper)
    }
}