package com.example.holybibleapp.domain.verses

import com.example.holybibleapp.core.*
import com.example.holybibleapp.data.books.BookData
import com.example.holybibleapp.data.books.BooksData
import com.example.holybibleapp.data.books.BooksDataToDomainMapper
import com.example.holybibleapp.data.chapters.ChapterData
import com.example.holybibleapp.data.chapters.ChaptersData
import com.example.holybibleapp.data.chapters.ChaptersDataToDomainMapper
import com.example.holybibleapp.data.verses.VersesData
import com.example.holybibleapp.data.verses.VersesDataToDomainMapper
import com.example.holybibleapp.domain.books.BooksDomain
import com.example.holybibleapp.domain.chapters.ChaptersDomain

class VersesAndBooksDomain(
    private val verses: VersesData,
    private val books: BooksData,
    private val chapters: ChaptersData,
    private val bookId: Read<Int>,
    private val chapterNumber: Read<Int>,
) : Abstract.Object<VersesDomain, VersesDataToDomainMapper<VersesDomain>> {
    override fun map(mapper: VersesDataToDomainMapper<VersesDomain>) = when {
        books is BooksData.Success && verses is VersesData.Success && chapters is ChaptersData.Success -> {
            val chapterId = chapterNumber.read()
            verses.map(
                mapper,
                books.getById(bookId.read()), chapterId, chapters.matches(chapterId)
            )
        }
        verses is VersesData.Fail -> verses.map(mapper, BuildString.Empty(), 0, true)
        chapters is ChaptersData.Fail -> {
            var errorType = ErrorType.GENERIC_ERROR
            val chaptersMapper = object :
                ChaptersDataToDomainMapper<ChaptersDomain>() {
                override fun map(data: Pair<List<ChapterData>, Abstract.Object<Unit, TextMapper>>) =
                    ChaptersDomain.Fail(ErrorType.GENERIC_ERROR)

                override fun map(e: Exception): ChaptersDomain {
                    errorType = errorType(e)
                    return ChaptersDomain.Fail(errorType)
                }
            }
            chapters.map(
                chaptersMapper,
                object : Abstract.Object<Unit, TextMapper> {
                    override fun map(mapper: TextMapper) = mapper.map("")
                }
            )
            VersesDomain.Fail(errorType)
        }

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