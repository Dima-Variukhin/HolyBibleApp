package com.example.holybibleapp.data.verses.cloud

import com.example.holybibleapp.data.chapters.cloud.ChapterCloud
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface VersesCloudDataSource {
    suspend fun fetcheVerses(bookId: Int, chapterId: Int): List<VerseCloud>

    class Base(
        private val service: VersesService,
        private val gson: Gson
    ) : VersesCloudDataSource {
        override suspend fun fetcheVerses(bookId: Int, chapterId: Int): List<VerseCloud> =
            gson.fromJson(
                service.fetchVerses(bookId, chapterId).string(),
                object : TypeToken<List<VerseCloud>>() {}.type
            )
    }
}