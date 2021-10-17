package com.example.holybibleapp.data.chapters.cache

import com.example.holybibleapp.core.DbWrapper
import com.example.holybibleapp.core.RealmProvider
import com.example.holybibleapp.core.CacheDataSource
import com.example.holybibleapp.core.Limits
import com.example.holybibleapp.data.chapters.ChapterData
import com.example.holybibleapp.presentation.chapters.ChapterId
import io.realm.Realm

interface ChaptersCacheDataSource : CacheDataSource<ChapterData> { //todo ccds
    fun fetchChapters(limits: Limits): List<ChapterDb>

    class Base(
        private val realmProvider: RealmProvider,
        private val mapper: ChapterDataToDbMapper<ChapterDb>
    ) : ChaptersCacheDataSource {
        override fun fetchChapters(limits: Limits): List<ChapterDb> {
            realmProvider.provide().use { realm ->
                val chapters = realm.where(ChapterDb::class.java)
                    .between("id", limits.min(), limits.max())
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
                        chapter.map(mapper, ChapterDbWrapper(realm))
                    }
                }
            }
        }

        private inner class ChapterDbWrapper(realm: Realm) : DbWrapper.Base<ChapterDb>(realm) {
            override fun dbClass() = ChapterDb::class.java
        }
    }
}