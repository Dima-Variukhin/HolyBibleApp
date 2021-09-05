package com.example.holybibleapp.presentation

import com.example.holybibleapp.R
import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.domain.BookDomain
import com.example.holybibleapp.domain.BookDomainToUiMapper
import com.example.holybibleapp.domain.ErrorType

sealed class BooksUi : Abstract.Object<Unit, BooksCommunication> {
    class Success(
        private val books: List<BookDomain>,
        private val bookMapper: BookDomainToUiMapper
    ) : BooksUi() {
        override fun map(mapper: BooksCommunication) {
            val booksUi = books.map {
                it.map(bookMapper)
            }
            mapper.map(booksUi)
        }
    }

    class Fail(
        private val errorType: ErrorType,
        private val resourceProvider: ResourceProvider
    ) : BooksUi() {
        override fun map(mapper: BooksCommunication) {
            val messageId = when (errorType) {
                ErrorType.NO_CONNECTION -> R.string.no_connection_message
                ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable_message
                else -> R.string.something_went_wrong
            }
            val message = resourceProvider.getString(messageId)
            mapper.map(listOf(BookUi.Fail(message)))
        }
    }
}