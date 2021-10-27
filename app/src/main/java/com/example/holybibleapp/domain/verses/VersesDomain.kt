package com.example.holybibleapp.domain.verses

import com.example.holybibleapp.core.BuildString
import com.example.holybibleapp.core.ErrorType

sealed class VersesDomain {
    abstract fun <T> map(mapper: VersesDomainToUiMapper<T>): T
    data class Success(
        private val list: List<VerseDomain>,
        private val title: BuildString,
        private val chapterNumber: Int
    ) : VersesDomain() {
        override fun <T> map(mapper: VersesDomainToUiMapper<T>) =
            mapper.map(Triple(list, title, chapterNumber))
    }

    data class Fail(private val errorType: ErrorType) : VersesDomain() {
        override fun <T> map(mapper: VersesDomainToUiMapper<T>) = mapper.map(errorType)
    }
}