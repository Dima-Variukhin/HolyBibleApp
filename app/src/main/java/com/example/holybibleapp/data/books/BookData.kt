package com.example.holybibleapp.data.books

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.DbWrapper
import com.example.holybibleapp.core.Matcher
import com.example.holybibleapp.core.Save
import com.example.holybibleapp.data.books.cache.BookDataToDbMapper
import com.example.holybibleapp.data.books.cache.BookDb
import com.example.holybibleapp.domain.books.BookDomain

class BookData(private val id: Int, private val name: String, private val testament: String) :
    Abstract.Object.ToDb<BookDb, BookDataToDbMapper>,
    Abstract.Object<BookDomain, BookDataToDomainMapper>,
    Matcher<TestamentTemp>,
    Save<TestamentTemp> {
    override fun map(mapper: BookDataToDomainMapper) = mapper.map(id, name)
    override fun mapBy(mapper: BookDataToDbMapper, db: DbWrapper<BookDb>) =
        mapper.mapToDb(id, name, testament, db)

    override fun matches(arg: TestamentTemp) = arg.matches(testament)
    override fun save(data: TestamentTemp) = data.save(testament)
}


