package com.example.holybibleapp.data.books

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.domain.books.BooksDomain

abstract class BooksDataToDomainMapper :
    Abstract.Mapper.DataToDomain.Base<List<BookData>, BooksDomain>()
