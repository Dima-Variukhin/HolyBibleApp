package com.example.holybibleapp.domain.books

import com.example.holybibleapp.data.books.BookDataToDomainMapper

class BaseBookDataToDomainMapper : BookDataToDomainMapper {
    override fun map(id: Int, name: String) = BookDomain.Base(id, name)
}