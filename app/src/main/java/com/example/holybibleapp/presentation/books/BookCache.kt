package com.example.holybibleapp.presentation.books

import android.content.Context
import com.example.holybibleapp.core.PreferencesProvider
import com.example.holybibleapp.core.Read
import com.example.holybibleapp.core.Save

interface BookCache : Save<Pair<Int, String>>, Read<Pair<Int, String>> {

    class Base(preferencesProvider: PreferencesProvider) : BookCache {
        private val sharedPreferences =
            preferencesProvider.provideSharedPreferences(BOOK_ID_FILENAME)

        override fun save(data: Pair<Int, String>) {
            sharedPreferences.edit().putInt(BOOK_ID_KEY, data.first).apply()
            sharedPreferences.edit().putString(BOOK_NAME_KEY, data.second).apply()
        }

        override fun read(): Pair<Int, String> {
            return Pair(
                sharedPreferences.getInt(BOOK_ID_KEY, 0),
                sharedPreferences.getString(BOOK_NAME_KEY, "") ?: ""
            )
        }

        private companion object {
            const val BOOK_ID_FILENAME = "bookId"
            const val BOOK_ID_KEY = "bookIdKey"
            const val BOOK_NAME_KEY = "bookNameKey"
        }
    }
}