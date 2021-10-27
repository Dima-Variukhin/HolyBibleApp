package com.example.holybibleapp.data.chapters

import com.example.holybibleapp.core.Read
import com.example.holybibleapp.core.BaseRepository
import com.example.holybibleapp.data.chapters.cache.ChapterDb
import com.example.holybibleapp.data.chapters.cache.ChaptersCacheDataSource
import com.example.holybibleapp.data.chapters.cache.ChaptersCacheMapper
import com.example.holybibleapp.data.chapters.cloud.ChapterCloud
import com.example.holybibleapp.data.chapters.cloud.ChaptersCloudDataSource
import com.example.holybibleapp.data.chapters.cloud.ChaptersCloudMapper
import com.example.holybibleapp.presentation.chapters.ChapterId
import java.lang.Exception


interface ChaptersRepository : BaseRepository<ChaptersData> {
    class Base(
        private val cloudDataSource: ChaptersCloudDataSource,
        private val cacheDataSource: ChaptersCacheDataSource,
        cloudMapper: ChaptersCloudMapper,
        cacheMapper: ChaptersCacheMapper,
        private val bookIdContainer: Read<Int>
    ) : BaseRepository.Base<ChapterDb, ChapterCloud, ChapterData, ChaptersData>(
        cacheDataSource, cloudMapper, cacheMapper
    ), ChaptersRepository {

        private val bookId by lazy {
            bookIdContainer.read()
        }

        override suspend fun fetchCloudData() = cloudDataSource.fetchChapters(bookId)
        override fun getCachedDataList() = cacheDataSource.fetchChapters(ChapterId.Base(bookId))
        override fun returnSuccess(list: List<ChapterData>) = ChaptersData.Success(list)
        override fun returnFail(e: Exception) = ChaptersData.Fail(e)
    }
}