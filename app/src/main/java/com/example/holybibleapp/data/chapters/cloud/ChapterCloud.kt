package com.example.holybibleapp.data.chapters.cloud

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.data.chapters.ChapterData
import com.example.holybibleapp.data.chapters.ToChapterMapper
import com.google.gson.annotations.SerializedName

interface ChapterCloud : Abstract.CloudObject {
    fun <T> map(mapper: ToChapterMapper<T>): T

    data class Base(
        @SerializedName("id")
        private val id: Int
    ) : ChapterCloud {
        override fun <T> map(mapper: ToChapterMapper<T>) = mapper.map(id)
    }
}