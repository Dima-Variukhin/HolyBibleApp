package com.example.holybibleapp.data.verses.cloud

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.data.verses.ToVerseMapper
import com.example.holybibleapp.data.verses.VerseData
import com.google.gson.annotations.SerializedName

data class VerseCloud(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("verseId")
    private val verseId: Int,
    @SerializedName("verse")
    private val text: String
) : Abstract.Object<VerseData, ToVerseMapper> {

    override fun map(mapper: ToVerseMapper) = mapper.map(id, verseId, text)
}