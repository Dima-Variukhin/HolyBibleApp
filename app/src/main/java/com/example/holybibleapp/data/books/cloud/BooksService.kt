package com.example.holybibleapp.data.books.cloud

import okhttp3.ResponseBody
import retrofit2.http.GET

interface BooksService {
    @GET("books")
    suspend fun fetchBooks(): ResponseBody
}