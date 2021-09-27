package com.example.holybibleapp.data.chapters.cache

import com.example.holybibleapp.core.DbWrapper
import com.example.holybibleapp.core.RealmProvider
import com.example.holybibleapp.core.Save
import com.example.holybibleapp.data.chapters.ChapterData
import com.example.holybibleapp.presentation.chapters.ChapterId
import io.realm.Realm

interface ChaptersCacheDataSource : Save<List<ChapterData>> {
    fun fetchChapters(bookId: Int): List<ChapterDb>

    class Base(
        private val realmProvider: RealmProvider,
        private val mapper: ChapterDataToDbMapper
    ) : ChaptersCacheDataSource {
        override fun fetchChapters(bookId: Int): List<ChapterDb> {
            val chapterId = ChapterId.Base(bookId, 0)
            realmProvider.provide().use { realm ->
                val chapters = realm.where(ChapterDb::class.java)
                    .between("id", chapterId.min(), chapterId.max())
                    .findAll()
                //используем copyFromRealm чтобы скопировать данные во внешнюю среду,
                // так как когда мы используем use{} все что находится вне скобок исчезает
                return realm.copyFromRealm(chapters)
            }
        }

        override fun save(data: List<ChapterData>) {
            realmProvider.provide().use { realm ->
                realm.executeTransaction {
                    data.forEach { chapter ->
                        chapter.mapBy(mapper, ChapterDbWrapper(realm))
                    }
                }
            }
        }

        private inner class ChapterDbWrapper(realm: Realm) : DbWrapper.Base<ChapterDb>(realm) {
            override fun dbClass() = ChapterDb::class.java
        }
    }
}