package com.example.holybibleapp.data.chapters.cache

import com.example.holybibleapp.data.chapters.ToChapterMapper
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ChapterDb : RealmObject(), ChapterRealm {
    @PrimaryKey
    var id: Int = -1

    override fun <T> map(mapper: ToChapterMapper<T>) = mapper.map(id)
}

interface ChapterRealm {
    fun <T> map(mapper: ToChapterMapper<T>): T
}