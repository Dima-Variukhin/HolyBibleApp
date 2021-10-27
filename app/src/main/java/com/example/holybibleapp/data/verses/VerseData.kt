package com.example.holybibleapp.data.verses

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.DbWrapper
import com.example.holybibleapp.data.verses.cache.VerseDataToDbMapper
import com.example.holybibleapp.data.verses.cache.VerseDb
import com.example.holybibleapp.domain.verses.VerseDomain
import io.realm.RealmObject

interface VerseData : Abstract.DataObject {
    fun <T> map(mapper: VerseDataToDomainMapper<T>): T
    fun <T : RealmObject> map(mapper: VerseDataToDbMapper<T>, db: DbWrapper<T>): T

    class Base(
        private val id: Int,
        private val verseId: Int,
        private val text: String
    ) : VerseData {
        override fun <T> map(mapper: VerseDataToDomainMapper<T>) = mapper.map(verseId, text)
        override fun <T : RealmObject> map(mapper: VerseDataToDbMapper<T>, db: DbWrapper<T>) =
            mapper.map(id, verseId, text, db)
    }
}