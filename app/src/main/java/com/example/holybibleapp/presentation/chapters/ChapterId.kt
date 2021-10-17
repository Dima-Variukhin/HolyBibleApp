package com.example.holybibleapp.presentation.chapters

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.DbWrapper
import com.example.holybibleapp.core.Limits
import com.example.holybibleapp.data.chapters.ChapterIdToUiMapper
import com.example.holybibleapp.data.chapters.cache.ChapterDb
import io.realm.RealmObject

interface ChapterId : Limits {
    fun <T> map(mapper: ChapterIdToUiMapper<T>): T
    fun <T : RealmObject> map(db: DbWrapper<T>): T

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

        override fun <T : RealmObject> map(db: DbWrapper<T>) = db.createObject(chapterIdGenerated)
        override fun <T> map(mapper: ChapterIdToUiMapper<T>) = mapper.map(chapterIdReal)

        private companion object {
            const val MULTIPLY = 1000
        }
    }
}