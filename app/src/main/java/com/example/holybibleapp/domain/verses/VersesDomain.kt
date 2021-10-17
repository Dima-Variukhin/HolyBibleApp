package com.example.holybibleapp.domain.verses

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.ErrorType
import com.example.holybibleapp.presentation.verses.VersesUi

sealed class VersesDomain {
    abstract fun <T> map(mapper: VersesDomainToUiMapper<T>): T
    data class Success(private val list: List<VerseDomain>) : VersesDomain() {
        override fun <T> map(mapper: VersesDomainToUiMapper<T>) = mapper.map(list)
    }

    data class Fail(private val errorType: ErrorType) : VersesDomain() {
        override fun <T> map(mapper: VersesDomainToUiMapper<T>) = mapper.map(errorType)
    }
}