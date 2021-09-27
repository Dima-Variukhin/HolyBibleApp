package com.example.holybibleapp.domain.books

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.ErrorType
import com.example.holybibleapp.presentation.books.BooksUi
//берет доменный обьект и мапит к юай
interface BooksDomainToUiMapper : Abstract.Mapper {
    fun map(books: List<BookDomain>) : BooksUi
    fun map(errorType: ErrorType) : BooksUi
}