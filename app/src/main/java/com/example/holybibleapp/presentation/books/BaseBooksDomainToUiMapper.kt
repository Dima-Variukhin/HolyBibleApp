package com.example.holybibleapp.presentation.books

import com.example.holybibleapp.R
import com.example.holybibleapp.core.ResourceProvider
import com.example.holybibleapp.domain.books.BookDomain
import com.example.holybibleapp.domain.books.BookDomainToUiMapper
import com.example.holybibleapp.domain.books.BooksDomainToUiMapper
import com.example.holybibleapp.core.ErrorType

class BaseBooksDomainToUiMapper(
    private val resourceProvider: ResourceProvider,
    private val bookMapper: BookDomainToUiMapper
) :
    BooksDomainToUiMapper {
    override fun map(books: List<BookDomain>) = BooksUi.Base(books.map {
        it.map(bookMapper)
    })

    override fun map(errorType: ErrorType): BooksUi {
        val messageId = when (errorType) {
            ErrorType.NO_CONNECTION -> R.string.no_connection_message
            ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable_message
            else -> R.string.something_went_wrong
        }
        val message = resourceProvider.getString(messageId)
        return BooksUi.Base(listOf(BookUi.Fail(message)))
    }
}

