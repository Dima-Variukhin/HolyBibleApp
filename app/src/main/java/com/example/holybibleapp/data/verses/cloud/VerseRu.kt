package com.example.holybibleapp.data.verses.cloud

import com.google.gson.annotations.SerializedName

data class VerseRu(@SerializedName("verse") private val verse: String) {

    fun toVerseCloud(bookId: Int, chapterId: Int, id: Int): VerseCloud.Base {
        val finalId = MULTIPLY * MULTIPLY * bookId + MULTIPLY * chapterId + id
        return VerseCloud.Base(finalId, id, verse)
    }

    private companion object {
        const val MULTIPLY = 1000
    }
}