package com.example.holybibleapp.domain.verses

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.BuildString
import com.example.holybibleapp.core.ErrorType
import com.example.holybibleapp.core.Read
import com.example.holybibleapp.data.books.BookData
import com.example.holybibleapp.data.books.BooksData
import com.example.holybibleapp.data.books.BooksDataToDomainMapper
import com.example.holybibleapp.data.verses.VersesData
import com.example.holybibleapp.data.verses.VersesDataToDomainMapper
import com.example.holybibleapp.domain.books.BooksDomain

class VersesAndBooksDomain(
    private val verses: VersesData,
    private val books: BooksData,
    private val bookId: Read<Int>,
    private val chapterNumber: Read<Int>,
) : Abstract.Object<VersesDomain, VersesDataToDomainMapper<VersesDomain>> {
    override fun map(mapper: VersesDataToDomainMapper<VersesDomain>) = when {
        books is BooksData.Success && verses is VersesData.Success ->
            verses.map(
                mapper,
                books.getById(bookId.read()), chapterNumber.read()
            )
        verses is VersesData.Fail -> verses.map(mapper, BuildString.Empty(), 0)

        else -> {
            var errorType: ErrorType = ErrorType.GENERIC_ERROR
            books.map(object : BooksDataToDomainMapper<BooksDomain>() {
                override fun map(data: List<BookData>) = BooksDomain.Fail(errorType)
                override fun map(e: Exception): BooksDomain {
                    errorType = errorType(e)
                    return BooksDomain.Fail(errorType)
                }
            })
            VersesDomain.Fail(errorType)
        }
    }
}