package com.example.holybibleapp.data.chapters.cache

import com.example.holybibleapp.core.DbWrapper
import com.example.holybibleapp.presentation.chapters.ChapterId
import io.realm.RealmObject

interface ChapterDataToDbMapper<T : RealmObject> {

    fun mapTo(chapterId: ChapterId, db: DbWrapper<T>): T

    class Base : ChapterDataToDbMapper<ChapterDb> {
        override fun mapTo(chapterId: ChapterId, db: DbWrapper<ChapterDb>) = chapterId.map(db)
    }
}