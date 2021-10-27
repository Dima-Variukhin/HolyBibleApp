package com.example.holybibleapp.presentation.verses

import com.example.holybibleapp.core.ListMapper
import com.example.holybibleapp.core.TextMapper

sealed class VersesUi {
    abstract fun map(list: ListMapper<VerseUi>, text: TextMapper)
    class Base(
        private val data: List<VerseUi>,
        private val title: String
    ) : VersesUi() {
        override fun map(list: ListMapper<VerseUi>, text: TextMapper) {
            list.map(data)
            text.map(title)
        }
    }
}