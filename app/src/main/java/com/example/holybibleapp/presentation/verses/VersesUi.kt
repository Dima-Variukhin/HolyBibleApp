package com.example.holybibleapp.presentation.verses

import com.example.holybibleapp.core.Abstract

sealed class VersesUi {
    abstract fun map(mapper: VersesCommunication)
    class Base(private val list: List<VerseUi>) : VersesUi() {
        override fun map(mapper: VersesCommunication) = mapper.map(list)
    }
}