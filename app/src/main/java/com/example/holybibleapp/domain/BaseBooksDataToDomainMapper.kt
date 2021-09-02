package com.example.holybibleapp.domain

import com.example.holybibleapp.core.Book
import com.example.holybibleapp.data.BooksDataToDomainMapper
import java.lang.Exception

class BaseBooksDataToDomainMapper : BooksDataToDomainMapper {
    override fun map(books: List<Book>) = BookDomain.Success(books)
    override fun map(e: Exception) = BookDomain.Fail(e)
}
