package com.example.holybibleapp.data.books.cache

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.DbWrapper
import io.realm.RealmObject


interface BookDataToDbMapper<E : RealmObject> : Abstract.Mapper {

    fun mapToDb(id: Int, name: String, testament: String, db: DbWrapper<E>): E

    class Base : BookDataToDbMapper<BookDb> {
        override fun mapToDb(id: Int, name: String, testament: String, db: DbWrapper<BookDb>) =
            db.createObject(id).apply {
                this.name = name
                this.testament = testament
            }
    }
}