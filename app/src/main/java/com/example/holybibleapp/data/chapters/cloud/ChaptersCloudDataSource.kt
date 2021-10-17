package com.example.holybibleapp.data.chapters.cloud

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface ChaptersCloudDataSource {

    suspend fun fetchChapters(bookId: Int): List<ChapterCloud.Base>

    class Base(
        private val service: ChaptersService,
        private val gson: Gson,
    ) : ChaptersCloudDataSource {
        override suspend fun fetchChapters(bookId: Int): List<ChapterCloud.Base> =
            gson.fromJson(
                service.fetchChapters(bookId).string(),
                object : TypeToken<List<ChapterCloud.Base>>() {}.type
            )
    }
}