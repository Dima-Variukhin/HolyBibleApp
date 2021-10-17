package com.example.holybibleapp.presentation.books

import android.content.Context
import com.example.holybibleapp.core.PreferencesProvider
import com.example.holybibleapp.core.Read
import com.example.holybibleapp.core.CacheDataSource
import com.example.holybibleapp.core.Save

interface CollapsedIdsCache : Save<Int>, Read<Set<Int>> {
    fun start()
    fun finish()

    abstract class Abstract(preferencesProvider: PreferencesProvider) : CollapsedIdsCache {
        private val sharedPreferences by lazy {
            preferencesProvider.provideSharedPreferences(getFileName())
        }
        private val idSet = mutableListOf<Int>()
        override fun read(): Set<Int> {
            //читаем с sharedPreferences set of strings и мапим к интам
            val set = sharedPreferences.getStringSet(getKey(), emptySet()) ?: emptySet()
            return set.mapTo(HashSet()) { it.toInt() }
        }

        override fun save(data: Int) {
            idSet.add(data)
        }

        override fun finish() {
            //преобразовываем list id в сет string
            val set = idSet.mapTo(HashSet()) { it.toString() }
            // кладем в sharedPreferences
            sharedPreferences.edit().putStringSet(getKey(), set).apply()
        }

        protected abstract fun getFileName(): String
        protected abstract fun getKey(): String

        override fun start() {
            idSet.clear()
        }
    }

    class Base(preferencesProvider: PreferencesProvider) :
        CollapsedIdsCache.Abstract(preferencesProvider) {

        override fun getFileName() = ID_LIST_NAME
        override fun getKey() = IDS_KEY

        companion object {
            const val ID_LIST_NAME = "collapsedItemsIdList"
            const val IDS_KEY = "collapsedItemsIdsKey"
        }
    }

    class Mock(preferencesProvider: PreferencesProvider) :
        CollapsedIdsCache.Abstract(preferencesProvider) {

        override fun getFileName() = ID_LIST_NAME
        override fun getKey() = IDS_KEY

        companion object {
            const val ID_LIST_NAME = "MockCollapsedItemsIdList"
            const val IDS_KEY = "MockCollapsedItemsIdsKey"
        }
    }

    class Empty : CollapsedIdsCache {
        override fun read() = emptySet<Int>()
        override fun save(data: Int) = Unit
        override fun start() = Unit
        override fun finish() = Unit
    }
}