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
import com.example.holybibleapp.sl.core.BaseModule
import com.example.holybibleapp.sl.core.CoreModule

class ChaptersModule(private val coreModule: CoreModule) : BaseModule<ChaptersViewModel> {
    override fun getViewModel() = ChaptersViewModel(
        getChaptersInteractor(),
        getChaptersCommunication(),
        getChaptersMapper(),
        coreModule.navigator,
        coreModule.bookCache,
        coreModule.chapterCache,
        coreModule.navigatorCommunication
    )

    private fun getChaptersInteractor() = ChaptersInteractor.Base(
        getChaptersRepository(),
        BaseChaptersDataToDomainMapper(BaseChapterDataToDomainMapper())
    )

    private fun getChaptersRepository() = ChaptersRepository.Base(
        ChaptersCloudDataSource.Base(
            coreModule.makeService(ChaptersService::class.java),
            coreModule.gson
        ),
        ChaptersCacheDataSource.Base(coreModule.realmProvider, ChapterDataToDbMapper.Base()),
        ChaptersCloudMapper.Base(ToChapterMapper.Cloud(coreModule.bookCache)),
        ChaptersCacheMapper.Base(ToChapterMapper.Db(coreModule.bookCache)),
        coreModule.bookCache
    )

    private fun getChaptersCommunication() = ChaptersCommunication.Base()

    private fun getChaptersMapper() = BaseChaptersDomainToUiMapper(
        BaseChapterDomainToUiMapper(ChapterIdToUiMapper.Base(coreModule.resourceProvider)),
        coreModule.resourceProvider
    )
}