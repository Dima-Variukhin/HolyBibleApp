package com.example.holybibleapp.sl.books

import com.example.holybibleapp.data.books.BooksRepository
import com.example.holybibleapp.data.books.ToBookMapper
import com.example.holybibleapp.data.books.cache.BookDataToDbMapper
import com.example.holybibleapp.data.books.cache.BooksCacheDataSource
import com.example.holybibleapp.data.books.cache.BooksCacheMapper
import com.example.holybibleapp.data.books.cloud.BooksCloudDataSource
import com.example.holybibleapp.data.books.cloud.BooksCloudMapper
import com.example.holybibleapp.data.books.cloud.BooksService
import com.example.holybibleapp.domain.books.BaseBookDataToDomainMapper
import com.example.holybibleapp.domain.books.BaseBooksDataToDomainMapper
import com.example.holybibleapp.domain.books.BooksInteractor
import com.example.holybibleapp.presentation.books.*
import com.example.holybibleapp.sl.core.BaseModule
import com.example.holybibleapp.sl.core.CoreModule

class BooksModule(
    private val coreModule: CoreModule,
    private val useMocks: Boolean
) : BaseModule<BooksViewModel> {
    override fun getViewModel(): BooksViewModel {
        val uiDataCache = getBooksUiDataCache()
        return BooksViewModel(
            getBooksInteractor(),
            getBooksMapper(uiDataCache),
            getBooksCommunication(),
            uiDataCache,
            coreModule.bookCache,
            coreModule.navigator,
            coreModule.navigatorCommunication
        )
    }

    private fun getBooksInteractor() = BooksInteractor.Base(
        getBooksRepository(),
        BaseBooksDataToDomainMapper(BaseBookDataToDomainMapper())
    )

    private fun getBooksRepository(): BooksRepository {
        val toBookMapper = ToBookMapper.Base()
        return if (useMocks)
            BooksRepository.Mock(
                getMockBooksCloudDataSource(),
                BooksCloudMapper.Base(toBookMapper)
            )
        else
            BooksRepository.Base(
                getBooksCloudDataSource(),
                BooksCacheDataSource.Base(coreModule.realmProvider, BookDataToDbMapper.Base()),
                BooksCloudMapper.Base(toBookMapper),
                BooksCacheMapper.Base(toBookMapper)
            )
    }

    private fun getBooksCloudDataSource() =
        BooksCloudDataSource.Base(getBooksService(), coreModule.gson)

    private fun getMockBooksCloudDataSource() =
        BooksCloudDataSource.Mock(coreModule.resourceProvider, coreModule.gson)

    private fun getBooksService() = coreModule.makeService(BooksService::class.java)

    private fun getBooksMapper(uiDataCache: UiDataCache) = BaseBooksDomainToUiMapper(
        coreModule.resourceProvider,
        BaseBookDomainToUiMapper(coreModule.resourceProvider),
        uiDataCache
    )

    private fun getBooksUiDataCache() = UiDataCache.Base(
        if (useMocks)
            CollapsedIdsCache.Mock(coreModule.resourceProvider)
        else
            CollapsedIdsCache.Base(coreModule.resourceProvider)
    )

    private fun getBooksCommunication() = BooksCommunication.Base()
}