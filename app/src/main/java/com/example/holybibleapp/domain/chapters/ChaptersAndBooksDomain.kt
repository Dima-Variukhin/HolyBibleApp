package com.example.holybibleapp.domain.chapters

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.ErrorType
import com.example.holybibleapp.core.Read
import com.example.holybibleapp.core.TextMapper
import com.example.holybibleapp.data.books.BookData
import com.example.holybibleapp.data.books.BooksData
import com.example.holybibleapp.data.books.BooksDataToDomainMapper
import com.example.holybibleapp.data.chapters.ChaptersData
import com.example.holybibleapp.data.chapters.ChaptersDataToDomainMapper
import com.example.holybibleapp.domain.books.BooksDomain

class ChaptersAndBooksDomain(
    private val chapters: ChaptersData,
    private val books: BooksData,
    private val bookId: Read<Int>
) : Abstract.Object<ChaptersDomain, ChaptersDataToDomainMapper<ChaptersDomain>> {

    private val empty by lazy {
        object : Abstract.Object<Unit, TextMapper> {
            override fun map(mapper: TextMapper) = Unit
        }
    }

    override fun map(mapper: ChaptersDataToDomainMapper<ChaptersDomain>) = when {
        //если книги и главы получены успешно
        books is BooksData.Success && chapters is ChaptersData.Success ->
            chapters.map(mapper, books.getById(bookId.read()))

        chapters is ChaptersData.Fail -> chapters.map(mapper, empty)

        else -> {
            var errorType: ErrorType = ErrorType.GENERIC_ERROR
            books.map(object : BooksDataToDomainMapper<BooksDomain>() {
                override fun map(data: List<BookData>) = BooksDomain.Fail(errorType)
                override fun map(e: Exception): BooksDomain {
                    errorType = errorType(e)
                    return BooksDomain.Fail(errorType)
                }
            })
            ChaptersDomain.Fail(errorType)
        }
    }
}