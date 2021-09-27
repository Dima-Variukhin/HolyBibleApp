package com.example.holybibleapp.domain.chapters

import com.example.holybibleapp.data.chapters.ChaptersDataToDomainMapper
import com.example.holybibleapp.data.chapters.ChaptersRepository

interface ChaptersInteractor {

    suspend fun showChapters(): ChaptersDomain

    class Base(
        private val repository: ChaptersRepository,
        private val mapper: ChaptersDataToDomainMapper
    ) : ChaptersInteractor {
        override suspend fun showChapters() = repository.fetchChapters().map(mapper)
    }
}