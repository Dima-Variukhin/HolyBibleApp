package com.example.holybibleapp.domain.verses

import com.example.holybibleapp.core.Read
import com.example.holybibleapp.data.books.BooksRepository
import com.example.holybibleapp.data.chapters.ChaptersRepository
import com.example.holybibleapp.data.chapters.cloud.ChaptersScrollPositionCache
import com.example.holybibleapp.data.verses.VersesDataToDomainMapper
import com.example.holybibleapp.data.verses.VersesRepository
import com.example.holybibleapp.data.verses.cloud.VersesScrollPositionCache
import com.example.holybibleapp.presentation.ScrollPosition
import com.example.holybibleapp.presentation.chapters.ChapterCache

interface VersesInteractor : ScrollPosition {
    suspend fun fetchVerses(): VersesDomain

    fun showNextChapter()

    class Base(
        private val repository: VersesRepository,
        private val mapper: VersesDataToDomainMapper<VersesDomain>,
        private val chapterNumber: Read<Int>,
        private val booksRepository: BooksRepository,
        private val bookIdContainer: Read<Int>,
        private val scrollPositionCache: VersesScrollPositionCache,
        private val chaptersRepository: ChaptersRepository,
        private val chapterCache: ChapterCache,
        private val chapterScrollPositionCache: ChaptersScrollPositionCache
    ) : VersesInteractor {

        override fun showNextChapter() {
            val data = chapterCache.read() + 1
            chapterCache.save(data)
            chapterScrollPositionCache.saveChaptersScrollPosition(data)
        }

        override suspend fun fetchVerses() = VersesAndBooksDomain(
            repository.fetchData(),
            booksRepository.fetchData(),
            chaptersRepository.fetchData(),
            bookIdContainer,
            chapterNumber,
        ).map(mapper)

        override fun scrollPosition() = scrollPositionCache.versesScrollPosition()
        override fun saveScrollPosition(position: Int) =
            scrollPositionCache.saveVersesScrollPosition(position)
    }
}