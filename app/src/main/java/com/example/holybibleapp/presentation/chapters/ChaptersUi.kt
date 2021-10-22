package com.example.holybibleapp.presentation.chapters


sealed class ChaptersUi {
    abstract fun map(mapper: ChaptersCommunication)
    class Base(
        private val chapters: List<ChapterUi>,
        private val bookName: String
    ) : ChaptersUi() {
        override fun map(mapper: ChaptersCommunication) = mapper.map(Pair(chapters, bookName))
    }
}
