package com.example.holybibleapp.domain.chapters

import com.example.holybibleapp.core.Read
import com.example.holybibleapp.data.books.BooksRepository
import com.example.holybibleapp.data.chapters.ChaptersDataToDomainMapper
import com.example.holybibleapp.data.chapters.ChaptersRepository
import com.example.holybibleapp.data.chapters.cloud.ChaptersScrollPositionCache
import com.example.holybibleapp.presentation.ScrollPosition

interface ChaptersInteractor : ScrollPosition {

    suspend fun showChapters(): ChaptersDomain
    class Base(
        private val repository: ChaptersRepository,
        private val mapper: ChaptersDataToDomainMapper<ChaptersDomain>,
        private val booksRepository: BooksRepository,
        private val bookIdContainer: Read<Int>,
        private val scrollPosition: ChaptersScrollPositionCache
    ) : ChaptersInteractor {
        override suspend fun showChapters() = ChaptersAndBooksDomain(
            repository.fetchData(),
            booksRepository.fetchData(),
            bookIdContainer
        ).map(mapper)

        override fun saveScrollPosition(position: Int) =
            scrollPosition.saveChaptersScrollPosition(position)

        override fun scrollPosition() = scrollPosition.chaptersScrollPosition()
    }
}