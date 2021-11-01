package com.example.holybibleapp.data.chapters

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.Match
import com.example.holybibleapp.core.TextMapper

sealed class ChaptersData : Abstract.DataObject, Match<Int> {
    abstract fun <T> map(
        mapper: ChaptersDataToDomainMapper<T>,
        book: Abstract.Object<Unit, TextMapper>
    ): T

    override fun matches(arg: Int) = false

    data class Success(private val chapters: List<ChapterData>) :
        ChaptersData() {
        override fun <T> map(
            mapper: ChaptersDataToDomainMapper<T>,
            book: Abstract.Object<Unit, TextMapper>
        ) = mapper.map(Pair(chapters, book))

        override fun matches(arg: Int) = arg == chapters.size
    }

    data class Fail(private val e: Exception) : ChaptersData() {
        override fun <T> map(
            mapper: ChaptersDataToDomainMapper<T>,
            book: Abstract.Object<Unit, TextMapper>
        ) = mapper.map(e)
    }
}