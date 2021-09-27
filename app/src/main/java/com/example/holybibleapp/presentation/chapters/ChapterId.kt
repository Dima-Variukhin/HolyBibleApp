package com.example.holybibleapp.presentation.chapters

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.DbWrapper
import com.example.holybibleapp.data.chapters.ChapterIdToUiMapper
import com.example.holybibleapp.data.chapters.cache.ChapterDb

interface ChapterId : Abstract.Object<ChapterUi, ChapterIdToUiMapper> {
    fun min(): Int
    fun max(): Int

    fun mapToDb(db: DbWrapper<ChapterDb>): ChapterDb

    class Base : ChapterId {
        private val bookId: Int
        private val chapterIdReal: Int
        private val chapterIdGenerated: Int

        constructor(
            bookId: Int,
            chapterIdReal: Int = 0,
            chapterIdGenerated: Int = 0
        ) {
            this.bookId = bookId
            if (chapterIdReal == 0) {
                this.chapterIdGenerated = chapterIdGenerated
                this.chapterIdReal = chapterIdGenerated % MULTIPLY
            } else {
                this.chapterIdReal = chapterIdReal
                this.chapterIdGenerated = MULTIPLY * bookId + chapterIdReal
            }
        }

        override fun min() = MULTIPLY * bookId
        override fun max() = MULTIPLY * (bookId + 1)

        override fun mapToDb(db: DbWrapper<ChapterDb>) = db.createObject(chapterIdGenerated)
        override fun map(mapper: ChapterIdToUiMapper) =
            mapper.map(chapterIdGenerated, chapterIdReal)

        private companion object {
            const val MULTIPLY = 1000
        }
    }
}