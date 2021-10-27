package com.example.holybibleapp.presentation.books

interface UiDataCache {

    fun cache(list: List<BookUi>): BooksUi
    fun changeState(id: Int): List<BookUi>
    fun saveState()


    class Base(private val cacheId: CollapsedIdsCache) : UiDataCache {
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

