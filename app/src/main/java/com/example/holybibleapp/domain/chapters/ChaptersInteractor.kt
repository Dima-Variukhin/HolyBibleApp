package com.example.holybibleapp.domain.chapters

import com.example.holybibleapp.core.Read
import com.example.holybibleapp.data.books.BooksRepository
import com.example.holybibleapp.data.chapters.ChaptersDataToDomainMapper
import com.example.holybibleapp.data.chapters.ChaptersRepository

interface ChaptersInteractor {

    suspend fun showChapters(): ChaptersDomain
    class Base(
        private val repository: ChaptersRepository,
        private val mapper: ChaptersDataToDomainMapper<ChaptersDomain>,
        private val booksRepository: BooksRepository,
        private val bookIdContainer: Read<Int>
    ) : ChaptersInteractor {
        override suspend fun showChapters() = ChaptersAndBooksDomain(
            repository.fetchData(),
            booksRepository.fetchData(),
            bookIdContainer
        ).map(mapper)
    }
}