package com.example.holybibleapp.presentation.chapters

import com.example.holybibleapp.core.ComparableTextMapper
import com.example.holybibleapp.core.Open
import com.example.holybibleapp.core.TextMapper

sealed class ChapterUi : ComparableTextMapper<ChapterUi>, Open<Show> {
    override fun open(show: Show) = Unit

    class Base(
        private val id: Int,
        private val text: String
    ) : ChapterUi() {
        override fun map(mapper: TextMapper) = mapper.map(text)

        override fun open(show: Show) = show.show(id)
    }

    class Fail(
        private val message: String
    ) : ChapterUi() {
        override fun map(mapper: TextMapper) = mapper.map(message)
    }

    object Progress : ChapterUi() {
        override fun map(mapper: TextMapper) = Unit
    }
}
