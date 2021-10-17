package com.example.holybibleapp.data.books.cloud

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.data.books.BookData
import com.example.holybibleapp.data.books.ToBookMapper

interface BooksCloudMapper : Abstract.Mapper.Data<List<BookCloud>, List<BookData>> {

    class Base(private val bookMapper: ToBookMapper<BookData>) : BooksCloudMapper {
        override fun map(data: List<BookCloud>) =
            data.map { bookCloud ->
                bookCloud.map(bookMapper)
            }
    }
}
