package com.example.holybibleapp.presentation.chapters

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.ListMapper
import com.example.holybibleapp.core.TextMapper


sealed class ChaptersUi {
    abstract fun map(list: ListMapper<ChapterUi>, text: TextMapper)
    class Base(
        private val chapters: List<ChapterUi>,
        private val bookName: Abstract.Object<Unit, TextMapper>
    ) : ChaptersUi() {
        override fun map(list: ListMapper<ChapterUi>, text: TextMapper) {
            list.map(chapters)
            bookName.map(text)
        }
    }
}
