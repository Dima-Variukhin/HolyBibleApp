package com.example.holybibleapp.data.books.cache

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.DbWrapper


interface BookDataToDbMapper : Abstract.Mapper {

    fun mapToDb(id: Int, name: String, testament: String, db: DbWrapper<BookDb>): BookDb

    class Base : BookDataToDbMapper {
        override fun mapToDb(
            id: Int, name: String, testament: String, db: DbWrapper<BookDb>
        ): BookDb {
            val bookDb = db.createObject(id)
            bookDb.name = name
            bookDb.testament = testament
            return bookDb
        }
    }
}