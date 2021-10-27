package com.example.holybibleapp.data.chapters

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.TextMapper

sealed class ChaptersData : Abstract.DataObject {
    abstract fun <T> map(
        mapper: ChaptersDataToDomainMapper<T>,
        book: Abstract.Object<Unit, TextMapper>
    ): T

    data class Success(private val chapters: List<ChapterData>) :
        ChaptersData() {
        override fun <T> map(
            mapper: ChaptersDataToDomainMapper<T>,
            book: Abstract.Object<Unit, TextMapper>
        ) = mapper.map(Pair(chapters, book))
    }

    data class Fail(private val e: Exception) : ChaptersData() {
        override fun <T> map(
            mapper: ChaptersDataToDomainMapper<T>,
            book: Abstract.Object<Unit, TextMapper>
        ) = mapper.map(e)
    }
}