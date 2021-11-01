package com.example.holybibleapp.presentation.verses

import com.example.holybibleapp.core.ComparableTextMapper
import com.example.holybibleapp.core.TextMapper

sealed class VerseUi : ComparableTextMapper<VerseUi> {
    class Base(private val text: String) : VerseUi() {
        override fun map(mapper: TextMapper) = mapper.map(text)
    }

    class Fail(private val text: String) : VerseUi() {
        override fun map(mapper: TextMapper) = mapper.map(text)
    }

    object Progress : VerseUi() {
        override fun map(mapper: TextMapper) = Unit
    }

    class Next(private val text: String) : VerseUi() {
        override fun map(mapper: TextMapper) = mapper.map(text)
    }
}
