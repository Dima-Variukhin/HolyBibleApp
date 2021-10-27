package com.example.holybibleapp.data.verses

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.BuildString

sealed class VersesData : Abstract.DataObject {

    abstract fun <T> map(
        mapper: VersesDataToDomainMapper<T>,
        book: BuildString,
        chapterNumber: Int
    ): T

    data class Success(private val verses: List<VerseData>) : VersesData() {
        override fun <T> map(
            mapper: VersesDataToDomainMapper<T>,
            book: BuildString,
            chapterNumber: Int
        ) = mapper.map(Triple(verses, book, chapterNumber))
    }

    data class Fail(private val e: Exception) : VersesData() {
        override fun <T> map(
            mapper: VersesDataToDomainMapper<T>,
            book: BuildString,
            chapterNumber: Int
        ) = mapper.map(e)
    }
}