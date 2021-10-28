package com.example.holybibleapp.domain.books

import com.example.holybibleapp.data.books.BooksDataToDomainMapper
import com.example.holybibleapp.data.books.BooksRepository
import com.example.holybibleapp.data.books.BooksScrollPositionCache
import com.example.holybibleapp.presentation.ScrollPosition

interface BooksInteractor : ScrollPosition {

    suspend fun fetchBooks(): BooksDomain

    class Base(
        private val booksRepository: BooksRepository,
        private val mapper: BooksDataToDomainMapper<BooksDomain>,
        private val scrollPosition: BooksScrollPositionCache
    ) : BooksInteractor {
        override suspend fun fetchBooks() = booksRepository.fetchData().map(mapper)
        override fun saveScrollPosition(position: Int) =
            scrollPosition.saveBooksScrollPosition(position)

        override fun scrollPosition() = scrollPosition.booksScrollPosition()
    }
}