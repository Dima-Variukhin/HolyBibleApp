package com.example.holybibleapp.presentation.chapters

import com.example.holybibleapp.core.Abstract

sealed class ChaptersUi : Abstract.Object<Unit, ChaptersCommunication> {
    class Base(private val chapters: List<ChapterUi>) : ChaptersUi() {
        override fun map(mapper: ChaptersCommunication) = mapper.map(chapters)
    }
}
