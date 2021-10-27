package com.example.holybibleapp.presentation.books

import com.example.holybibleapp.core.PreferencesProvider
import com.example.holybibleapp.core.Read
import com.example.holybibleapp.core.Save

interface BookCache : Save<Int>, Read<Int> {

    class Base(preferencesProvider: PreferencesProvider) : BookCache {
        private val sharedPreferences =
            preferencesProvider.provideSharedPreferences(BOOK_ID_FILENAME)

        override fun save(data: Int) = sharedPreferences.edit().putInt(BOOK_ID_KEY, data).apply()
        override fun read() = sharedPreferences.getInt(BOOK_ID_KEY, 0)

        private companion object {
            const val BOOK_ID_FILENAME = "bookId"
            const val BOOK_ID_KEY = "bookIdKey"
            const val BOOK_NAME_KEY = "bookNameKey"
        }
    }
}