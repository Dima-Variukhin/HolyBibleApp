package com.example.holybibleapp.data.books

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.domain.books.BookDomain

interface BookDataToDomainMapper<T> : Abstract.Mapper {
    fun map(id: Int, name: String): T
}