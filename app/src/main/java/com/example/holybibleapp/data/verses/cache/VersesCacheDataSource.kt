package com.example.holybibleapp.data.verses.cache

import com.example.holybibleapp.core.DbWrapper
import com.example.holybibleapp.core.RealmProvider
import com.example.holybibleapp.core.CacheDataSource
import com.example.holybibleapp.core.Limits
import com.example.holybibleapp.data.verses.VerseData
import io.realm.Realm

interface VersesCacheDataSource : CacheDataSource<VerseData> {

    fun fetchVerses(limits: Limits): List<VerseDb>

    class Base(
        private val realmProvider: RealmProvider,
        private val mapper: VerseDataToDbMapper<VerseDb>
    ) : VersesCacheDataSource {
        override fun fetchVerses(limits: Limits): List<VerseDb> {
            realmProvider.provide().use { realm ->
                val verses = realm.where(VerseDb::class.java)
                    .between("id", limits.min(), limits.max())
                    .findAll()
                return realm.copyFromRealm(verses)
            }
        }

        override fun save(data: List<VerseData>) {
            realmProvider.provide().use { realm ->
                realm.executeTransaction {
                    data.forEach { verse ->
                        verse.map(mapper, VerseDbWrapper(realm))
                    }
                }
            }
        }

        private inner class VerseDbWrapper(realm: Realm) : DbWrapper.Base<VerseDb>(realm) {
            override fun dbClass() = VerseDb::class.java
        }
    }
}