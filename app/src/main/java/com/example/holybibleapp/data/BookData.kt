package com.example.holybibleapp.data

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.data.cache.BookDb
import com.example.holybibleapp.data.cache.DbWrapper
import com.example.holybibleapp.domain.BookDomain
import com.example.holybibleapp.domain.TestamentType
import io.realm.Realm

class BookData(
    private val id: Int,
    private val name: String,
    private val testament: String
) :
    ToBookDb<BookDb, BookDataToDbMapper>,
    Abstract.Object<BookDomain, BookDataToDomainMapper> {
    override fun map(mapper: BookDataToDomainMapper) = mapper.map(id, name)
    override fun mapTo(mapper: BookDataToDbMapper, db: DbWrapper) =
        mapper.mapToDb(id, name, testament, db)

    fun compare(temp: TestamentTemp) = temp.matches(testament)
    fun saveTestament(temp: TestamentTemp) = temp.save(testament)
}

interface TestamentTemp {
    fun save(testament: String)
    fun matches(testament: String): Boolean
    fun isEmpty(): Boolean
    class Base : TestamentTemp {
        private var temp: String = ""
        override fun save(testament: String) {
            temp = testament
        }

        override fun matches(testament: String) = temp == testament
        override fun isEmpty() = temp.isEmpty()
    }
}

interface ToBookDb<T, M : Abstract.Mapper> {

    fun mapTo(mapper: M, db: DbWrapper): T
}