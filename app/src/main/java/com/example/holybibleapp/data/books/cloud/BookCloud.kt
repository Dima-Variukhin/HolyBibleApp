package com.example.holybibleapp.data.books.cloud

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.data.books.BookData
import com.example.holybibleapp.data.books.ToBookMapper
import com.google.gson.annotations.SerializedName

//есть букКлауд который умеет мапиться с помощью туБукМаммер но неизвестно к чему
//так мы сделали слабую связь
interface BookCloud : Abstract.CloudObject {
    fun <T> map(mapper: ToBookMapper<T>): T

    data class Base(
        @SerializedName("id")
        private val id: Int,
        @SerializedName("name")
        private val name: String,
        @SerializedName("testament")
        private val testament: String
    ) : BookCloud {
        override fun <T> map(mapper: ToBookMapper<T>): T = mapper.map(id, name, testament)
    }
}
