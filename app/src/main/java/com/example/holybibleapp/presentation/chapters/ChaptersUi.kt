package com.example.holybibleapp.presentation.chapters


sealed class ChaptersUi {
    abstract fun map(mapper: ChaptersCommunication)
    class Base(private val chapters: List<ChapterUi>) : ChaptersUi() {
        override fun map(mapper: ChaptersCommunication) = mapper.map(chapters)
    }
}
