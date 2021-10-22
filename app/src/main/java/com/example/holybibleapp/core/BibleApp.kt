package com.example.holybibleapp.core

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.holybibleapp.BuildConfig
import com.example.holybibleapp.BuildConfig.USE_MOCKS
import com.example.holybibleapp.NavigationCommunication
import com.example.holybibleapp.MainViewModel

import com.example.holybibleapp.data.books.*
import com.example.holybibleapp.data.books.cache.BookDataToDbMapper
import com.example.holybibleapp.data.books.cache.BooksCacheDataSource
import com.example.holybibleapp.data.books.cache.BooksCacheMapper
import com.example.holybibleapp.data.books.cloud.BooksCloudDataSource
import com.example.holybibleapp.data.books.cloud.BooksCloudMapper
import com.example.holybibleapp.data.books.cloud.BooksService
import com.example.holybibleapp.data.chapters.ChapterIdToUiMapper
import com.example.holybibleapp.data.chapters.ChaptersRepository
import com.example.holybibleapp.data.chapters.ToChapterMapper
import com.example.holybibleapp.data.chapters.cache.ChapterDataToDbMapper
import com.example.holybibleapp.data.chapters.cache.ChaptersCacheDataSource
import com.example.holybibleapp.data.chapters.cache.ChaptersCacheMapper
import com.example.holybibleapp.data.chapters.cloud.ChaptersCloudDataSource
import com.example.holybibleapp.data.chapters.cloud.ChaptersCloudMapper
import com.example.holybibleapp.data.chapters.cloud.ChaptersService
import com.example.holybibleapp.domain.books.BaseBookDataToDomainMapper
import retrofit2.Retrofit
import com.example.holybibleapp.domain.books.BaseBooksDataToDomainMapper
import com.example.holybibleapp.domain.books.BooksInteractor
import com.example.holybibleapp.domain.chapters.BaseChapterDataToDomainMapper
import com.example.holybibleapp.domain.chapters.BaseChaptersDataToDomainMapper
import com.example.holybibleapp.domain.chapters.ChaptersDomainToUiMapper
import com.example.holybibleapp.domain.chapters.ChaptersInteractor
import com.example.holybibleapp.presentation.Navigator
import com.example.holybibleapp.presentation.books.*
import com.example.holybibleapp.presentation.chapters.*
import com.example.holybibleapp.sl.books.BooksFactory
import com.example.holybibleapp.sl.books.BooksModule
import com.example.holybibleapp.sl.chapters.ChaptersFactory
import com.example.holybibleapp.sl.chapters.ChaptersModule
import com.example.holybibleapp.sl.core.CoreModule
import com.example.holybibleapp.sl.core.ViewModelsFactory
import com.example.holybibleapp.sl.languages.LanguagesFactory
import com.example.holybibleapp.sl.languages.LanguagesModule
import com.example.holybibleapp.sl.verses.VersesFactory
import com.example.holybibleapp.sl.verses.VersesModule
import com.google.gson.Gson
import io.realm.Realm
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class BibleApp : Application() {
    private val coreModule = CoreModule(USE_MOCKS)

    private val booksModule by lazy {
        BooksModule(coreModule, USE_MOCKS)
    }

    val factory by lazy {
        ViewModelsFactory(
            coreModule,
            BooksModule(coreModule, USE_MOCKS),
            ChaptersModule(coreModule, USE_MOCKS),
            VersesModule(coreModule, USE_MOCKS),
            LanguagesModule(coreModule)
        )
    }

    override fun onCreate() {
        super.onCreate()
        coreModule.init(this)
    }

    fun <T : ViewModel> getViewModel(modelClass: Class<T>, owner: ViewModelStoreOwner): T =
        ViewModelProvider(owner, factory).get(modelClass)
}
