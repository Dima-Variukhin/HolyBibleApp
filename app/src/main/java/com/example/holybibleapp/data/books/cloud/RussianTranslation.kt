package com.example.holybibleapp.data.books.cloud

import com.example.holybibleapp.core.Content
import com.example.holybibleapp.core.Match
import com.example.holybibleapp.data.books.ToBookMapper
import com.example.holybibleapp.data.chapters.cloud.ChapterCloud
import com.example.holybibleapp.data.chapters.cloud.ChapterRu
import com.google.gson.annotations.SerializedName

data class RussianTranslation(
    @SerializedName("version")
    private val allData: Map<String, BookRu>
) : Content<BookCloud> {
    override fun contentAsList() = allData.map { it.value }
}

data class BookRu(
    @SerializedName("book_name") private val name: String,
    @SerializedName("book") private val content: Map<String, ChapterRu>,
    @SerializedName("book_nr") private val number: Int
) : BookCloud, Match<Int>, Content<ChapterCloud> {

    override fun <T> map(mapper: ToBookMapper<T>): T {
        return mapper.map(
            number, name,
            if (number < NEW_TESTAMENT_POSITION) OLD_TESTAMENT else NEW_TESTAMENT
        )
    }

    override fun matches(arg: Int) = number == arg

    override fun contentAsList() = content.map { it.value }

    private companion object {
        const val NEW_TESTAMENT_POSITION = 40
        const val OLD_TESTAMENT = "old"
        const val NEW_TESTAMENT = "new"
    }
}
