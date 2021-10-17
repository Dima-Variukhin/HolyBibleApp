package com.example.holybibleapp.data.verses.cache

import com.example.holybibleapp.core.DbWrapper
import io.realm.RealmObject

interface VerseDataToDbMapper<T : RealmObject> {
    fun map(id: Int, verseId: Int, text: String, db: DbWrapper<T>): T

    class Base : VerseDataToDbMapper<VerseDb> {
        override fun map(id: Int, verseId: Int, text: String, db: DbWrapper<VerseDb>) =
            db.createObject(id).apply {
                this.verseId = verseId
                this.text = text
            }
    }
}