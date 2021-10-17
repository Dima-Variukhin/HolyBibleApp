package com.example.holybibleapp.data.books

import com.example.holybibleapp.core.Repository
import com.example.holybibleapp.data.books.cache.BookDb
import com.example.holybibleapp.data.books.cache.BooksCacheDataSource
import com.example.holybibleapp.data.books.cache.BooksCacheMapper
import com.example.holybibleapp.data.books.cloud.BookCloud
import com.example.holybibleapp.data.books.cloud.BooksCloudDataSource
import com.example.holybibleapp.data.books.cloud.BooksCloudMapper
import java.lang.Exception

interface BooksRepository : Repository<BooksData> {
    class Base(
        private val cloudDataSource: BooksCloudDataSource,
        private val cacheDataSource: BooksCacheDataSource,
        booksCloudMapper: BooksCloudMapper,
        booksCacheMapper: BooksCacheMapper
    ) : Repository.Base<BookDb, BookCloud, BookData, BooksData>(
        cacheDataSource, booksCloudMapper, booksCacheMapper
    ), BooksRepository {
        override suspend fun fetchCloudData() = cloudDataSource.fetchBooks()
        override fun getCachedDataList() = cacheDataSource.read()
        override fun returnSuccess(list: List<BookData>) = BooksData.Success(list)
        override fun returnFail(e: Exception) = BooksData.Fail(e)
    }

    class Mock(
        private val cloudDataSource: BooksCloudDataSource,
        private val booksCloudMapper: BooksCloudMapper,
    ) : BooksRepository {
        override suspend fun fetchData(): BooksData {
            val books = cloudDataSource.fetchBooks()
            return BooksData.Success(booksCloudMapper.map(books))
        }
    }
}