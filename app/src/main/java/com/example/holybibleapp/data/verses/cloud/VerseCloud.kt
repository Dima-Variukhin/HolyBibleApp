package com.example.holybibleapp.data.verses.cloud

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.data.verses.ToVerseMapper
import com.example.holybibleapp.data.verses.VerseData
import com.google.gson.annotations.SerializedName

interface VerseCloud : Abstract.CloudObject {
    fun <T> map(mapper: ToVerseMapper<T>): T

    data class Base(
        @SerializedName("id")
        private val id: Int,
        @SerializedName("verseId")
        private val verseId: Int,
        @SerializedName("verse")
        private val text: String
    ) : VerseCloud {
        override fun <T> map(mapper: ToVerseMapper<T>) = mapper.map(id, verseId, text)
    }
}