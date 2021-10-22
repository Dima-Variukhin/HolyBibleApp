package com.example.holybibleapp.data.chapters

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.domain.chapters.ChaptersDomain
import java.lang.Exception

sealed class ChaptersData : Abstract.DataObject {
    abstract fun <T> map(mapper: ChaptersDataToDomainMapper<T>, bookName: String): T

    data class Success(private val chapters: List<ChapterData>) :
        ChaptersData() {
        override fun <T> map(mapper: ChaptersDataToDomainMapper<T>, bookName: String) =
            mapper.map(Pair(chapters, bookName))

    }

    data class Fail(private val e: Exception) : ChaptersData() {
        override fun <T> map(mapper: ChaptersDataToDomainMapper<T>, bookName: String) =
            mapper.map(e)
    }
}