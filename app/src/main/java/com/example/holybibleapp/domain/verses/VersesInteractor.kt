package com.example.holybibleapp.domain.verses

import com.example.holybibleapp.data.verses.VersesDataToDomainMapper
import com.example.holybibleapp.data.verses.VersesRepository

interface VersesInteractor {
    suspend fun fetchVerses(): VersesDomain

    class Base(
        private val repository: VersesRepository,
        private val mapper: VersesDataToDomainMapper<VersesDomain>
    ) : VersesInteractor {
        override suspend fun fetchVerses() = repository.fetchData().map(mapper)
    }
}