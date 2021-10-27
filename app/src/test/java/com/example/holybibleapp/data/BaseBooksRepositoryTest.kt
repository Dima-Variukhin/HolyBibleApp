package com.example.holybibleapp.data

import com.example.holybibleapp.data.books.BookData
import com.example.holybibleapp.data.books.ToBookMapper


abstract class BaseBooksRepositoryTest {

    protected class TestToBookMapper : ToBookMapper {
        override fun map(id: Int, name: String, testament: String) = BookData(id, name, testament)
    }
}