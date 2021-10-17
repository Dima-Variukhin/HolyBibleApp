package com.example.holybibleapp.data.verses

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.domain.verses.VersesDomain
import java.lang.Exception

sealed class VersesData : Abstract.DataObject {

    abstract fun <T> map(mapper: VersesDataToDomainMapper<T>): T

    data class Success(private val verses: List<VerseData>) : VersesData() {
        override fun <T> map(mapper: VersesDataToDomainMapper<T>) = mapper.map(verses)
    }

    data class Fail(private val e: Exception) : VersesData() {
        override fun <T> map(mapper: VersesDataToDomainMapper<T>) = mapper.map(e)
    }
}