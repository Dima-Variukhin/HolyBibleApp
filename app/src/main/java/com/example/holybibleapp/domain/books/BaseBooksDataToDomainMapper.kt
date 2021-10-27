package com.example.holybibleapp.domain.books

import com.example.holybibleapp.data.books.BookData
import com.example.holybibleapp.data.books.BookDataToDomainMapper
import com.example.holybibleapp.data.books.BooksDataToDomainMapper
import com.example.holybibleapp.data.books.TestamentTemp
import java.lang.Exception


class BaseBooksDataToDomainMapper(private val bookMapper: BookDataToDomainMapper<BookDomain>) :
    BooksDataToDomainMapper<BooksDomain>() {
    override fun map(data: List<BookData>): BooksDomain {
        val domainList = mutableListOf<BookDomain>()
        val temp = TestamentTemp.Base()
        data.forEach { bookData ->
            if (!bookData.matches(temp)) {
                val testamentType = if (temp.isEmpty())
                    TestamentType.OLD
                else
                    TestamentType.NEW
                domainList.add(
                    BookDomain.Testament(testamentType)
                )
                bookData.save(temp)
            }
            domainList.add(bookData.map(bookMapper))
        }
        return BooksDomain.Success(domainList)
    }

    override fun map(e: Exception) = BooksDomain.Fail(errorType(e))
}

