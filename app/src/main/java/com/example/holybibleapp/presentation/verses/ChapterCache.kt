package com.example.holybibleapp.presentation.verses

import androidx.annotation.RequiresPermission
import com.example.holybibleapp.core.PreferencesProvider
import com.example.holybibleapp.core.Read
import com.example.holybibleapp.core.Save

interface ChapterCache : Save<Int>, Read<Int> {

    class Base(preferencesProvider: PreferencesProvider) : ChapterCache {

        private val sharedPreferences = preferencesProvider.provideSharedPreferences(
            CHAPTER_ID_FILENAME
        )

        override fun save(data: Int) = sharedPreferences.edit().putInt(
            CHAPTER_ID_KEY, data
        ).apply()

        override fun read() = sharedPreferences.getInt(CHAPTER_ID_KEY, 0)

        private companion object {
            const val CHAPTER_ID_FILENAME = "chapterIdFileName"
            const val CHAPTER_ID_KEY = "chapterIdKey"
        }
    }
}