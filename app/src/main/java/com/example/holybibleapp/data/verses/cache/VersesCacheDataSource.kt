package com.example.holybibleapp.data.verses.cache

import com.example.holybibleapp.core.DbWrapper
import com.example.holybibleapp.core.RealmProvider
import com.example.holybibleapp.core.Save
import com.example.holybibleapp.data.verses.VerseData
import io.realm.Realm

interface VersesCacheDataSource : Save<List<VerseData>> {

    suspend fun fetchVerses(bookId: Int, chapterId: Int): List<VerseDb>

    class Base(
        private val realmProvider: RealmProvider,
        private val mapper: VerseDataToDbMapper
    ) : VersesCacheDataSource {
        override suspend fun fetchVerses(bookId: Int, chapterId: Int): List<VerseDb> {
            val min = 1_000_000 * bookId + 100 * chapterId
            val max = 1_000_000 * bookId + 1000 * (chapterId + 1)
            realmProvider.provide().use { realm ->
                val verses = realm.where(VerseDb::class.java)
                    .between("id", min, max)
                    .findAll()
                return realm.copyFromRealm(verses)
            }
        }

        override fun save(data: List<VerseData>) {
            realmProvider.provide().use { realm ->
                realm.executeTransaction {
                    data.forEach { verse ->
                        verse.mapBy(mapper, VerseDbWrapper(realm))
                    }
                }
            }
        }

        private inner class VerseDbWrapper(realm: Realm) : DbWrapper.Base<VerseDb>(realm) {
            override fun dbClass() = VerseDb::class.java
        }
    }
}