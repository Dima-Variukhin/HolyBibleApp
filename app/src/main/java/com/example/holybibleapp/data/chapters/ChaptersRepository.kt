package com.example.holybibleapp.data.chapters

import com.example.holybibleapp.core.Read
import com.example.holybibleapp.data.chapters.cache.ChaptersCacheDataSource
import com.example.holybibleapp.data.chapters.cache.ChaptersCacheMapper
import com.example.holybibleapp.data.chapters.cloud.ChaptersCloudDataSource
import com.example.holybibleapp.data.chapters.cloud.ChaptersCloudMapper
import java.lang.Exception


interface ChaptersRepository {

    suspend fun fetchChapters(): ChaptersData

    class Base(
        private val cloudDataSource: ChaptersCloudDataSource,
        private val cacheDataSource: ChaptersCacheDataSource,
        private val cloudMapper: ChaptersCloudMapper,
        private val cacheMapper: ChaptersCacheMapper,
        private val bookIdContainer: Read<Pair<Int, String>>
    ) : ChaptersRepository {
        override suspend fun fetchChapters() = try {
            val bookId = bookIdContainer.read().first
            val chaptersCacheList = cacheDataSource.fetchChapters(bookId)
            if (chaptersCacheList.isEmpty()) {
                val chaptersCloudList = cloudDataSource.fetchChapters(bookId)
                val chapters = cloudMapper.map(chaptersCloudList, bookId)
                cacheDataSource.save(chapters)
                ChaptersData.Success(chapters)
            } else {
                ChaptersData.Success(cacheMapper.map(chaptersCacheList))
            }
        } catch (e: Exception) {
            ChaptersData.Fail(e)

        }
    }
}