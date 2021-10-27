package com.example.holybibleapp.data.chapters

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.DbWrapper
import com.example.holybibleapp.data.chapters.cache.ChapterDataToDbMapper
import com.example.holybibleapp.data.chapters.cache.ChapterDb
import com.example.holybibleapp.domain.chapters.ChapterDomain
import com.example.holybibleapp.presentation.chapters.ChapterId
import io.realm.RealmObject

interface ChapterData : Abstract.DataObject {
    fun <T : RealmObject> map(mapper: ChapterDataToDbMapper<T>, db: DbWrapper<T>): T
    fun <T> map(mapper: ChapterDataToDomainMapper<T>): T

    data class Base(private val chapterId: ChapterId) : ChapterData {
        override fun <T : RealmObject> map(mapper: ChapterDataToDbMapper<T>, db: DbWrapper<T>) =
            mapper.mapTo(chapterId, db)

        override fun <T> map(mapper: ChapterDataToDomainMapper<T>) = mapper.map(chapterId)
    }
}