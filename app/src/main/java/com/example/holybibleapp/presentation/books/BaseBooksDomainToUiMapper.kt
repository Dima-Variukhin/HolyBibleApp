package com.example.holybibleapp.presentation.books

import com.example.holybibleapp.R
import com.example.holybibleapp.core.ResourceProvider
import com.example.holybibleapp.domain.books.BookDomain
import com.example.holybibleapp.domain.books.BookDomainToUiMapper
import com.example.holybibleapp.domain.books.BooksDomainToUiMapper
import com.example.holybibleapp.core.ErrorType

class BaseBooksDomainToUiMapper(
    resourceProvider: ResourceProvider,
    private val bookMapper: BookDomainToUiMapper<BookUi>,
    private val uiDataCache: UiDataCache
) : BooksDomainToUiMapper<BooksUi>(resourceProvider) {
    override fun map(data: List<BookDomain>) = uiDataCache.cache(data.map { it.map(bookMapper) })

    override fun map(errorType: ErrorType) = BooksUi.Base(listOf(BookUi.Fail(error(errorType))))
}


