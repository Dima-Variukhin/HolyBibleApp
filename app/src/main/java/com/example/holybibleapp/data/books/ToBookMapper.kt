package com.example.holybibleapp.data.books

import com.example.holybibleapp.core.Abstract

interface ToBookMapper<T> : Abstract.Mapper {

    fun map(id: Int, name: String, testament: String): T

    class Base : ToBookMapper<BookData> {
        override fun map(id: Int, name: String, testament: String) =
            BookData.Base(id, name, testament)
    }
}