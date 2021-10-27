package com.example.holybibleapp.data.verses

import com.example.holybibleapp.core.Read
import com.example.holybibleapp.core.BaseRepository
import com.example.holybibleapp.data.verses.cache.VerseDb
import com.example.holybibleapp.data.verses.cache.VersesCacheDataSource
import com.example.holybibleapp.data.verses.cache.VersesCacheMapper
import com.example.holybibleapp.data.verses.cache.VersesLimits
import com.example.holybibleapp.data.verses.cloud.VerseCloud
import com.example.holybibleapp.data.verses.cloud.VersesCloudDataSource
import com.example.holybibleapp.data.verses.cloud.VersesCloudMapper
import java.lang.Exception

interface VersesRepository : BaseRepository<VersesData> {
    class Base(
        private val cloudDataSource: VersesCloudDataSource,
        private val cacheDataSource: VersesCacheDataSource,
        cloudMapper: VersesCloudMapper,
        cacheMapper: VersesCacheMapper,
        private val chapterIdContainer: Read<Int>,
        private val bookIdContainer: Read<Int>
    ) : BaseRepository.Base<VerseDb, VerseCloud, VerseData, VersesData>(
        cacheDataSource, cloudMapper, cacheMapper
    ), VersesRepository {

        private val bookId by lazy { bookIdContainer.read() }
        private val chapterId by lazy { chapterIdContainer.read() }

        override suspend fun fetchCloudData() = cloudDataSource.fetchVerses(bookId, chapterId)
        override fun getCachedDataList() =
            cacheDataSource.fetchVerses(VersesLimits(bookId, chapterId))

        override fun returnSuccess(list: List<VerseData>) = VersesData.Success(list)
        override fun returnFail(e: Exception) = VersesData.Fail(e)
    }
}