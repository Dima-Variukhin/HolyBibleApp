package com.example.holybibleapp.data.books

import com.example.holybibleapp.core.*
import com.example.holybibleapp.data.books.cache.BookDataToDbMapper
import io.realm.RealmObject

interface BookData : Match<TestamentTemp>, Save<TestamentTemp>, Abstract.DataObject,
    Abstract.Object<Unit, TextMapper>, BuildString {

    fun <T> map(mapper: BookDataToDomainMapper<T>): T
    fun <T : RealmObject> map(mapper: BookDataToDbMapper<T>, db: DbWrapper<T>): T

    fun find(id: Int): Boolean
    data class Base(private val id: Int, private val name: String, private val testament: String) :
        BookData {
        override fun <T> map(mapper: BookDataToDomainMapper<T>) = mapper.map(id, name)

        override fun <T : RealmObject> map(mapper: BookDataToDbMapper<T>, db: DbWrapper<T>) =
            mapper.mapToDb(id, name, testament, db)

        override fun find(id: Int) = this.id == id
        override fun build(resourceProvider: ResourceProvider, id: Int, arg: Any) =
            resourceProvider.getString(id, name, arg)

        override fun matches(arg: TestamentTemp) = arg.matches(testament)
        override fun save(data: TestamentTemp) = data.save(testament)
        override fun map(mapper: TextMapper) = mapper.map(name)
    }

    // замена !! чтобы небыло краша
    class Empty : BookData {
        override fun build(resourceProvider: ResourceProvider, id: Int, arg: Any) = ""
        override fun matches(arg: TestamentTemp) = false
        override fun save(data: TestamentTemp) = Unit
        override fun <T> map(mapper: BookDataToDomainMapper<T>) = mapper.map(0, "")
        override fun <T : RealmObject> map(mapper: BookDataToDbMapper<T>, db: DbWrapper<T>) =
            mapper.mapToDb(0, "", "", db)

        override fun find(id: Int) = false
        override fun map(mapper: TextMapper) = mapper.map("")
    }
}


