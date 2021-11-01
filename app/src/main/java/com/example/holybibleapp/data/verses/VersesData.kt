package com.example.holybibleapp.data.verses

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.BuildString

sealed class VersesData : Abstract.DataObject {

    abstract fun <T> map(
        mapper: VersesDataToDomainMapper<T>,
        book: BuildString,
        chapterNumber: Int,
        lastChapter: Boolean
    ): T

    data class Success(private val verses: List<VerseData>) : VersesData() {
        override fun <T> map(
            mapper: VersesDataToDomainMapper<T>,
            book: BuildString,
            chapterNumber: Int,
            lastChapter: Boolean
        ) = mapper.map(Triple(verses, book, Pair(chapterNumber, lastChapter)))
    }

    data class Fail(private val e: Exception) : VersesData() {
        override fun <T> map(
            mapper: VersesDataToDomainMapper<T>,
            book: BuildString,
            chapterNumber: Int,
            lastChapter: Boolean
        ) = mapper.map(e)
    }
}