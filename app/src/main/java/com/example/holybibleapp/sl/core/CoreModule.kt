package com.example.holybibleapp.sl.core

import android.content.Context
import com.example.holybibleapp.MainViewModel
import com.example.holybibleapp.NavigationCommunication
import com.example.holybibleapp.core.RealmProvider
import com.example.holybibleapp.core.ResourceProvider
import com.example.holybibleapp.presentation.Navigator
import com.example.holybibleapp.presentation.books.BookCache
import com.example.holybibleapp.presentation.chapters.ChapterCache
import com.google.gson.Gson
import io.realm.Realm
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

class CoreModule : BaseModule<MainViewModel> {

    private companion object {
        const val BASE_URL = "https://bible-go-api.rkeplin.com/v1/"
    }

    lateinit var resourceProvider: ResourceProvider
    lateinit var gson: Gson
    lateinit var retrofit: Retrofit
    lateinit var realmProvider: RealmProvider
    lateinit var navigator: Navigator
    lateinit var navigatorCommunication: NavigationCommunication
    lateinit var bookCache: BookCache
    lateinit var chapterCache: ChapterCache

    fun init(context: Context) {
        Realm.init(context)
        val client = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .build()
        gson = Gson()
        realmProvider = RealmProvider.Base(context)
        resourceProvider = ResourceProvider.Base(context)
        bookCache = BookCache.Base(resourceProvider)
        chapterCache = ChapterCache.Base(resourceProvider)
        navigator = Navigator.Base(resourceProvider)
        navigatorCommunication = NavigationCommunication.Base()
    }

    fun <T> makeService(clazz: Class<T>): T = retrofit.create(clazz)

    override fun getViewModel() = MainViewModel(navigator, navigatorCommunication)

}
