package com.example.holybibleapp.data.books.cloud

import android.content.res.Resources
import com.example.holybibleapp.R
import com.example.holybibleapp.core.RawResourceReader
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader

interface BooksCloudDataSource {

    suspend fun fetchBooks(): List<BookCloud>

    abstract class Abstract(private val gson: Gson) : BooksCloudDataSource {
        override suspend fun fetchBooks(): List<BookCloud> = gson.fromJson(
            getDataAsString(),
            object : TypeToken<List<BookCloud>>() {}.type
        )

        protected abstract suspend fun getDataAsString(): String
    }

    class Base(
        private val service: BooksService,
        gson: Gson,
    ) : BooksCloudDataSource.Abstract(gson) {
        override suspend fun getDataAsString() = service.fetchBooks().string()
    }

    class Mock(
        private val rawResourceReader: RawResourceReader,
        gson: Gson
    ) : BooksCloudDataSource.Abstract(gson) {
        override suspend fun getDataAsString() =
            rawResourceReader.readText(R.raw.books_successful_responce)
    }
}
