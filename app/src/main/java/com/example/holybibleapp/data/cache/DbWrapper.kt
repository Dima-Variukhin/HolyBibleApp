package com.example.holybibleapp.data.cache

import io.realm.Realm

interface DbWrapper {
    fun createObject(id: Int): BookDb

    class Base(private val realm: Realm) : DbWrapper {
        override fun createObject(id: Int): BookDb {
            return realm.createObject(BookDb::class.java, id)
        }
    }
}