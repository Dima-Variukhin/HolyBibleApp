package com.example.holybibleapp.data.verses

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.domain.verses.VersesDomain
import java.lang.Exception

sealed class VersesData : Abstract.Object<VersesDomain, VersesDataToDomainMapper> {

    data class Success(private val verses: List<VerseData>) : VersesData() {
        override fun map(mapper: VersesDataToDomainMapper) = mapper.map(verses)
    }

    data class Fail(private val e: Exception) : VersesData() {
        override fun map(mapper: VersesDataToDomainMapper) = mapper.map(e)
    }
}