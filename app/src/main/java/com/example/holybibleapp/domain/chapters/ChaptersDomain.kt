package com.example.holybibleapp.domain.chapters

import com.example.holybibleapp.core.ErrorType

sealed class ChaptersDomain {
    abstract fun <T> map(mapper: ChaptersDomainToUiMapper<T>): T

    data class Success(private val chapters: List<ChapterDomain>, private val bookName: String) :
        ChaptersDomain() {
        override fun <T> map(mapper: ChaptersDomainToUiMapper<T>) =
            mapper.map(Pair(chapters, bookName))
    }

    data class Fail(private val errorType: ErrorType) : ChaptersDomain() {
        override fun <T> map(mapper: ChaptersDomainToUiMapper<T>) = mapper.map(errorType)
    }
}