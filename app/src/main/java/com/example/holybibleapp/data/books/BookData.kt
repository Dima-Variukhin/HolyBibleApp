package com.example.holybibleapp.data.books

import com.example.holybibleapp.core.*
import com.example.holybibleapp.data.books.cache.BookDataToDbMapper
import com.example.holybibleapp.data.books.cache.BookDb
import com.example.holybibleapp.domain.books.BookDomain
import io.realm.RealmObject

interface BookData : Matcher<TestamentTemp>, Save<TestamentTemp>, Abstract.DataObject {

    fun <T> map(mapper: BookDataToDomainMapper<T>): T
    fun <T : RealmObject> map(mapper: BookDataToDbMapper<T>, db: DbWrapper<T>): T

    data class Base(private val id: Int, private val name: String, private val testament: String) :
        BookData {
        override fun <T> map(mapper: BookDataToDomainMapper<T>) = mapper.map(id, name)

        override fun <T : RealmObject> map(mapper: BookDataToDbMapper<T>, db: DbWrapper<T>) =
            mapper.mapToDb(id, name, testament, db)


        override fun matches(arg: TestamentTemp) = arg.matches(testament)
        override fun save(data: TestamentTemp) = data.save(testament)
    }
}


