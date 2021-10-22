package com.example.holybibleapp.data.books

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.domain.books.BooksDomain
import java.lang.Exception


sealed class BooksData : Abstract.DataObject {
    abstract fun <T> map(mapper: BooksDataToDomainMapper<T>): T

    data class Success(private val books: List<BookData>) : BooksData() {
        override fun <T> map(mapper: BooksDataToDomainMapper<T>) = mapper.map(books)

        fun getById(id: Int) = books.find { it.find(id) }!!
    }

    data class Fail(private val e: Exception) : BooksData() {
        override fun <T> map(mapper: BooksDataToDomainMapper<T>) = mapper.map(e)
    }
}
