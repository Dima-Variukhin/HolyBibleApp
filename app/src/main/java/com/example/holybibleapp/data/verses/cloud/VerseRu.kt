package com.example.holybibleapp.data.verses.cloud

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.data.verses.ToVerseMapper
import com.google.gson.annotations.SerializedName

data class VerseRu(@SerializedName("verse") private val text: String) :
    Abstract.Object<VerseCloud, VerseToWrapperMapper> {
    override fun map(mapper: VerseToWrapperMapper) = mapper.map(text)
}

data class VerseRuWrapper(
    private val finalId: Int,
    private val id: Int,
    private val verse: String
) : VerseCloud {
    override fun <T> map(mapper: ToVerseMapper<T>) = mapper.map(finalId, id, verse)
}
