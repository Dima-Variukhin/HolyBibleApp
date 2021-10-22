package com.example.holybibleapp.domain.verses

import com.example.holybibleapp.R
import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.ErrorType
import com.example.holybibleapp.core.Read
import com.example.holybibleapp.core.ResourceProvider
import com.example.holybibleapp.data.books.BookData
import com.example.holybibleapp.data.books.BooksData
import com.example.holybibleapp.data.books.BooksDataToDomainMapper
import com.example.holybibleapp.data.verses.VerseData
import com.example.holybibleapp.data.verses.VersesData
import com.example.holybibleapp.data.verses.VersesDataToDomainMapper
import com.example.holybibleapp.domain.books.BooksDomain
import java.lang.Exception

class VersesAndBooksDomain(
    private val verses: VersesData,
    private val books: BooksData,
    private val bookId: Read<Int>,
    private val chapterNumber: Read<Int>,
    private val resourceProvider: ResourceProvider
) : Abstract.Object<VersesDomain, VersesDataToDomainMapper<VersesDomain>> {
    override fun map(mapper: VersesDataToDomainMapper<VersesDomain>) = when {
        books is BooksData.Success && verses is VersesData.Success ->
            verses.map(
                mapper,
                resourceProvider.getString(
                    R.string.book_and_chapter,
                    books.getById(bookId.read()).name(),
                    chapterNumber.read()
                )
            )
        verses is VersesData.Fail -> verses.map(mapper, "")

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