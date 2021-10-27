package com.example.holybibleapp.data.verses.cloud

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface VersesService {
    @GET("books/{bookId}/chapters/{chapterId}")
    suspend fun fetchVerses(
        @Path("bookId") bookId: Int,
        @Path("chapterId") chapterId: Int
    ): ResponseBody
}