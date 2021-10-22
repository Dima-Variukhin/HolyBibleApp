package com.example.holybibleapp.data.chapters.cloud

import com.example.holybibleapp.core.Content
import com.example.holybibleapp.core.Matcher
import com.example.holybibleapp.data.verses.cloud.VerseRu
import com.google.gson.annotations.SerializedName

data class ChapterRu(
    @SerializedName("chapter_nr") private val number: Int,
    @SerializedName("chapter") private val content: Map<String, VerseRu>
) : Matcher<Int>, Content<Pair<Int, VerseRu>> {
    override fun contentAsList() = content.map { (key, value) -> Pair(key.toInt(), value) }

    override fun matches(arg: Int) = number == arg
}