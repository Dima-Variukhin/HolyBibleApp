package com.example.holybibleapp.core

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.holybibleapp.BuildConfig
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
import com.google.gson.Gson
import io.realm.Realm
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class BibleApp : Application() {

    private companion object {
        const val BASE_URL = "https://bible-go-api.rkeplin.com/v1/"
    }

    fun chaptersFactory() = ChaptersFactory(
        getChaptersInteractor(),
        getChaptersCommunication(),
        getChaptersMapper(),
        navigator,
        bookCache
    )

    lateinit var mainViewModel: MainViewModel
    private var booksViewModel: BooksViewModel? = null

    private lateinit var resourceProvider: ResourceProvider
    private lateinit var gson: Gson
    private lateinit var retrofit: Retrofit
    private lateinit var realmProvider: RealmProvider
    private lateinit var navigator: Navigator
    private lateinit var navigatorCommunication: NavigationCommunication
    private lateinit var bookCache: BookCache

    private val useMocks = BuildConfig.USE_MOCKS

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
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
        realmProvider = RealmProvider.Base()
        resourceProvider = ResourceProvider.Base(this)
        bookCache = BookCache.Base(this)
        navigator = Navigator.Base(this)
        navigatorCommunication = NavigationCommunication.Base()
        mainViewModel = MainViewModel(navigator, navigatorCommunication)

        val instanceProvider = if (useMocks) MocksProvider() else BaseProvider()
    }

    fun provideBooksViewModel(): BooksViewModel {
        val collapsedIdCache = if (useMocks) CollapsedIdsCache.Mock(this)
        else
            CollapsedIdsCache.Base(this)
        val uiDataCache = UiDataCache.Base(collapsedIdCache)
        val service = retrofit.create(BooksService::class.java)
        val communication = BooksCommunication.Base()
        val cloudDataSource = if (useMocks)
            BooksCloudDataSource.Mock(resources, gson)
        else
            BooksCloudDataSource.Base(service, gson)
        val cacheDataSource =
            BooksCacheDataSource.Base(RealmProvider.Base(), BookDataToDbMapper.Base())
        val toBookMapper = ToBookMapper.Base()
        val booksCloudMapper = BooksCloudMapper.Base(toBookMapper)
        val booksCacheMapper = BooksCacheMapper.Base(toBookMapper)
        val booksRepository = BooksRepository.Base(
            cloudDataSource,
            cacheDataSource,
            booksCloudMapper,
            booksCacheMapper
        )
        val booksInteractor = BooksInteractor.Base(
            booksRepository,
            BaseBooksDataToDomainMapper(BaseBookDataToDomainMapper())
        )
        booksViewModel = BooksViewModel(
            booksInteractor,
            BaseBooksDomainToUiMapper(resourceProvider, BaseBookDomainToUiMapper(resourceProvider)),
            communication,
            uiDataCache,
            bookCache,
            navigator,
            navigatorCommunication
        )
        return booksViewModel!!
    }

    private fun getChaptersRepository() = ChaptersRepository.Base(
        ChaptersCloudDataSource.Base(
            retrofit.create(ChaptersService::class.java), gson
        ),
        ChaptersCacheDataSource.Base(realmProvider, ChapterDataToDbMapper.Base()),
        ChaptersCloudMapper.Base(ToChapterMapper.Cloud(bookCache)),
        ChaptersCacheMapper.Base(ToChapterMapper.Db(bookCache)),
        bookCache
    )

    private fun getChaptersInteractor() = ChaptersInteractor.Base(
        getChaptersRepository(),
        BaseChaptersDataToDomainMapper(BaseChapterDataToDomainMapper())
    )

    private fun getChaptersCommunication() = ChaptersCommunication.Base()
    private fun getChaptersMapper() = BaseChaptersDomainToUiMapper(
        BaseChapterDomainToUiMapper(ChapterIdToUiMapper.Base(resourceProvider)), resourceProvider
    )
}

class ChaptersFactory(
    private val chaptersInteractor: ChaptersInteractor,
    private val chaptersCommunication: ChaptersCommunication,
    private val chaptersMapper: ChaptersDomainToUiMapper,
    private val navigator: ChaptersNavigator,
    private val bookCache: Read<Pair<Int, String>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ChaptersViewModel(
            chaptersInteractor,
            chaptersCommunication,
            chaptersMapper,
            navigator,
            bookCache
        ) as T
    }
}

//class BooksFactory(
//
//) : ViewModelProvider.Factory {
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        return BooksViewModel()
//    }
//
//}
