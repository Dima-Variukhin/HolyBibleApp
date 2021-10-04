package com.example.holybibleapp.data.verses.cache

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.DbWrapper

interface VerseDataToDbMapper : Abstract.Mapper {
    fun mapToDb(id: Int, verseId: Int, text: String, db: DbWrapper<VerseDb>): VerseDb

    class Base : VerseDataToDbMapper {
        override fun mapToDb(id: Int, verseId: Int, text: String, db: DbWrapper<VerseDb>) =
            db.createObject(id).apply {
                this.verseId = verseId
                this.text = text
            }
    }
}