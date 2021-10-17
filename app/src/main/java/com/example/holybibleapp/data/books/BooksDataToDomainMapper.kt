package com.example.holybibleapp.data.books

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.domain.books.BooksDomain

abstract class BooksDataToDomainMapper<T> :
    Abstract.Mapper.DataToDomain.Base<List<BookData>, T>()
