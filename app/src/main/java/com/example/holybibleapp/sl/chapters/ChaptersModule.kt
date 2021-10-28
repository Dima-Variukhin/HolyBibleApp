package com.example.holybibleapp.sl.chapters

import com.example.holybibleapp.data.chapters.ChapterIdToUiMapper
import com.example.holybibleapp.data.chapters.ChaptersRepository
import com.example.holybibleapp.data.chapters.ToChapterMapper
import com.example.holybibleapp.data.chapters.cache.ChapterDataToDbMapper
import com.example.holybibleapp.data.chapters.cache.ChaptersCacheDataSource
import com.example.holybibleapp.data.chapters.cache.ChaptersCacheMapper
import com.example.holybibleapp.data.chapters.cloud.ChaptersCloudDataSource
import com.example.holybibleapp.data.chapters.cloud.ChaptersCloudMapper
import com.example.holybibleapp.data.chapters.cloud.ChaptersService
import com.example.holybibleapp.domain.chapters.BaseChapterDataToDomainMapper
import com.example.holybibleapp.domain.chapters.BaseChaptersDataToDomainMapper
import com.example.holybibleapp.domain.chapters.ChaptersInteractor
import com.example.holybibleapp.presentation.chapters.BaseChapterDomainToUiMapper
import com.example.holybibleapp.presentation.chapters.BaseChaptersDomainToUiMapper
import com.example.holybibleapp.presentation.chapters.ChaptersCommunication
import com.example.holybibleapp.presentation.chapters.ChaptersViewModel
import com.example.holybibleapp.sl.books.BooksModule
import com.example.holybibleapp.sl.core.BaseModule
import com.example.holybibleapp.sl.core.CoreModule

class ChaptersModule(
    private val coreModule: CoreModule,
    private val booksModule: BooksModule,
    private val useMocks: Boolean
) : BaseModule<ChaptersViewModel> {
    override fun getViewModel() = ChaptersViewModel(
        getChaptersInteractor(),
        getChaptersCommunication(),
        getChaptersMapper(),
        coreModule.navigator,
        coreModule.chapterCache,
        coreModule.navigatorCommunication,
        coreModule.resourceProvider
    )

    private fun getChaptersInteractor() = ChaptersInteractor.Base(
        getChaptersRepository(),
        BaseChaptersDataToDomainMapper(BaseChapterDataToDomainMapper()),
        booksModule.repository(),
        coreModule.bookCache,
        coreModule.scrollPositionCache
    )

    private fun getChaptersRepository() = ChaptersRepository.Base(
        if (useMocks)
            ChaptersCloudDataSource.Mock(coreModule.resourceProvider, coreModule.gson)
        else
            ChaptersCloudDataSource.Base(
                coreModule.language,
                ChaptersCloudDataSource.English(
                    coreModule.makeService(ChaptersService::class.java),
                    coreModule.gson
                ),
                ChaptersCloudDataSource.Russian(coreModule.resourceProvider, coreModule.gson)
            ),
        ChaptersCacheDataSource.Base(coreModule.realmProvider, ChapterDataToDbMapper.Base()),
        getCloudMapper(),
        ChaptersCacheMapper.Base(ToChapterMapper.Db(coreModule.bookCache)),
        coreModule.bookCache
    )

    private fun getCloudMapper() =
        ChaptersCloudMapper.Base(ToChapterMapper.Cloud(coreModule.bookCache))

    private fun getChaptersCommunication() = ChaptersCommunication.Base()

    private fun getChaptersMapper() = BaseChaptersDomainToUiMapper(
        BaseChapterDomainToUiMapper(ChapterIdToUiMapper.Base(coreModule.resourceProvider)),
        coreModule.resourceProvider
    )
}