package com.example.holybibleapp.presentation

import android.content.Context

interface UiDataCache {

    fun cache(list: List<BookUi>): BooksUi
    fun changeState(id: Int): List<BookUi>
    fun saveState()


    class Base(private val cacheId: IdCache) : UiDataCache {
        private val cachedList = ArrayList<BookUi>()
        override fun cache(list: List<BookUi>): BooksUi {
            cachedList.clear()
            cachedList.addAll(list)
            var newList: List<BookUi> = ArrayList(list)
            val ids = cacheId.read()
            ids.forEach { id ->
                newList = changeState(id)
            }
            return BooksUi.Base(newList)
        }

        override fun changeState(id: Int): List<BookUi> {
            val newList = ArrayList<BookUi>()

            val item = cachedList.find {
                it.matches(id)
            }

            var add = false
            cachedList.forEachIndexed { index, book ->
                if (book is BookUi.Testament) {
                    if (item == book) {
                        val element = book.changeState()
                        cachedList[index] = element
                        newList.add(element)
                        add = !element.isCollapsed()
                    } else {
                        newList.add(book)
                        add = !book.isCollapsed()
                    }
                } else {
                    if (add) newList.add(book)
                }
            }
            return newList
        }

        override fun saveState() {
            cacheId.start()
            //берем кэш лист и фильтруем по состояниям коллапсд
            cachedList.filter {
                it.isCollapsed()
            }.map {
                it.saveId(cacheId)
            }
            cacheId.finish()
        }
    }
}

interface IdCache {
    fun read(): Set<Int>
    fun save(id: Int)
    fun start()
    fun finish()

    class Base(private val context: Context) : IdCache {
        private val sharedPreferences =
            context.getSharedPreferences(ID_LIST_NAME, Context.MODE_PRIVATE)
        private val idSet = mutableListOf<Int>()
        override fun read(): Set<Int> {
            //читаем с sharedPreferences set of strings и мапим к интам
            val set = sharedPreferences.getStringSet(IDS_KEY, emptySet()) ?: emptySet()
            return set.mapTo(HashSet()) { it.toInt() }
        }

        override fun save(id: Int) {
            idSet.add(id)
        }

        override fun finish() {
            //преобразовываем list id в сет string
            val set = idSet.mapTo(HashSet()) { it.toString() }
            // кладем в sharedPreferences
            sharedPreferences.edit().putStringSet(IDS_KEY, set).apply()
        }

        override fun start() {
            idSet.clear()
        }

        companion object {
            const val ID_LIST_NAME = "collapsedItemsIdList"
            const val IDS_KEY = "collapsedItemsIdsKey"
        }

    }

    class Empty : IdCache {
        override fun read() = emptySet<Int>()
        override fun save(id: Int) = Unit
        override fun start() = Unit
        override fun finish() = Unit
    }
}

