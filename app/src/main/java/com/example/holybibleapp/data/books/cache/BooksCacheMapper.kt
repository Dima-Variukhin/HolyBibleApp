package com.example.holybibleapp.data.books.cache

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.data.books.BookData
import com.example.holybibleapp.data.books.ToBookMapper

interface BooksCacheMapper : Abstract.Mapper.Data<List<BookDb>, List<BookData>> {

    class Base(private val mapper: ToBookMapper<BookData>) : BooksCacheMapper {
        override fun map(data: List<BookDb>) = data.map { bookDb -> bookDb.map(mapper) }
    }
}