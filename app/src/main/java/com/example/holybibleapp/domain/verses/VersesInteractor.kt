package com.example.holybibleapp.domain.verses

import com.example.holybibleapp.core.Read
import com.example.holybibleapp.data.books.BooksRepository
import com.example.holybibleapp.data.verses.VersesDataToDomainMapper
import com.example.holybibleapp.data.verses.VersesRepository
import com.example.holybibleapp.data.verses.cloud.VersesScrollPositionCache
import com.example.holybibleapp.presentation.ScrollPosition

interface VersesInteractor : ScrollPosition {
    suspend fun fetchVerses(): VersesDomain
    class Base(
        private val repository: VersesRepository,
        private val mapper: VersesDataToDomainMapper<VersesDomain>,
        private val chapterNumber: Read<Int>,
        private val booksRepository: BooksRepository,
        private val bookIdContainer: Read<Int>,
        private val scrollPositionCache: VersesScrollPositionCache
    ) : VersesInteractor {
        override suspend fun fetchVerses() = VersesAndBooksDomain(
            repository.fetchData(),
            booksRepository.fetchData(),
            bookIdContainer,
            chapterNumber,
        ).map(mapper)

        override fun scrollPosition() = scrollPositionCache.versesScrollPosition()
        override fun saveScrollPosition(position: Int) =
            scrollPositionCache.saveVersesScrollPosition(position)
    }
}