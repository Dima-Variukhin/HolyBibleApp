package com.example.holybibleapp.data.net

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.Book
import com.google.gson.annotations.SerializedName

data class BookCloud(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("name")
    private val name: String
) : Abstract.Object<Book, BookCloudMapper>() {
    override fun map(mapper: BookCloudMapper) = mapper.map(id, name)
}
