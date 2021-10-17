package com.example.holybibleapp.domain.books

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.presentation.books.BookUi

interface BookDomainToUiMapper<T> : Abstract.Mapper {
    fun map(id: Int, name: String): T
}