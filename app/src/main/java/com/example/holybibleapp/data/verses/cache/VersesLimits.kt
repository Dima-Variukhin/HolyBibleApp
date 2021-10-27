package com.example.holybibleapp.data.verses.cache

import com.example.holybibleapp.core.Limits

class VersesLimits(
    private val bookId: Int,
    private val chapterId: Int
) : Limits {
    override fun min(): Int {
        return MULTIPLY * (MULTIPLY * bookId + chapterId)
    }

    override fun max(): Int {
        return MULTIPLY * (MULTIPLY * bookId + chapterId + 1)
    }

    private companion object {
        const val MULTIPLY = 1000
    }
}