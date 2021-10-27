package com.example.holybibleapp.domain.books

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.ErrorType
import com.example.holybibleapp.core.ResourceProvider
import com.example.holybibleapp.presentation.books.BooksUi

//берет доменный обьект и мапит к юай
abstract class BooksDomainToUiMapper<T>(resourceProvider: ResourceProvider) :
    Abstract.Mapper.DomainToUi.Base<List<BookDomain>, T>(resourceProvider) {

}