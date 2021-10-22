package com.example.holybibleapp.domain.verses

import com.example.holybibleapp.R
import com.example.holybibleapp.core.Read
import com.example.holybibleapp.core.ResourceProvider
import com.example.holybibleapp.data.books.BooksRepository
import com.example.holybibleapp.data.verses.VersesDataToDomainMapper
import com.example.holybibleapp.data.verses.VersesRepository
import com.example.holybibleapp.presentation.books.BookCache

interface VersesInteractor {
    suspend fun fetchVerses(): VersesDomain
    class Base(
        private val repository: VersesRepository,
        private val mapper: VersesDataToDomainMapper<VersesDomain>,
        private val resourceProvider: ResourceProvider,
        private val chapterNumber: Read<Int>,
        private val booksRepository: BooksRepository,
        private val bookIdContainer: Read<Int>
    ) : VersesInteractor {
        override suspend fun fetchVerses() = VersesAndBooksDomain(
            repository.fetchData(),
            booksRepository.fetchData(),
            bookIdContainer,
            chapterNumber,
            resourceProvider
        ).map(mapper)
    }
}