package com.example.holybibleapp.data.verses.cloud

import com.example.holybibleapp.core.Abstract

interface VerseToWrapperMapper : Abstract.Mapper {

    fun map(text: String): VerseCloud

    class Base(
        private val bookId: Int,
        private val chapterId: Int,
        private val id: Int
    ) : VerseToWrapperMapper {
        override fun map(text: String): VerseCloud {
            val finalId = MULTIPLY * MULTIPLY * bookId + MULTIPLY * chapterId + id
            return VerseRuWrapper(finalId, id, text)
        }

        private companion object {
            const val MULTIPLY = 1000

        }
    }
}