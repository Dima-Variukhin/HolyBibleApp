package com.example.holybibleapp.sl.verses

import com.example.holybibleapp.data.verses.ToVerseMapper
import com.example.holybibleapp.data.verses.VersesRepository
import com.example.holybibleapp.data.verses.cache.VerseDataToDbMapper
import com.example.holybibleapp.data.verses.cache.VersesCacheDataSource
import com.example.holybibleapp.data.verses.cache.VersesCacheMapper
import com.example.holybibleapp.data.verses.cloud.VersesCloudDataSource
import com.example.holybibleapp.data.verses.cloud.VersesCloudMapper
import com.example.holybibleapp.data.verses.cloud.VersesService
import com.example.holybibleapp.domain.verses.BaseVerseDataToDomainMapper
import com.example.holybibleapp.domain.verses.BaseVersesDataToDomainMapper
import com.example.holybibleapp.domain.verses.VersesInteractor
import com.example.holybibleapp.presentation.verses.BaseVerseDomainToUiMapper
import com.example.holybibleapp.presentation.verses.BaseVersesDomainToUiMapper
import com.example.holybibleapp.presentation.verses.VersesCommunication
import com.example.holybibleapp.presentation.verses.VersesViewModel
import com.example.holybibleapp.sl.books.BooksModule
import com.example.holybibleapp.sl.core.BaseModule
import com.example.holybibleapp.sl.core.CoreModule

class VersesModule(
    private val coreModule: CoreModule,
    private val booksModule: BooksModule,
    private val useMocks: Boolean
) : BaseModule<VersesViewModel> {
    override fun getViewModel() = VersesViewModel(
        coreModule.navigator,
        getInteractor(),
        VersesCommunication.Base(),
        BaseVersesDomainToUiMapper(
            BaseVerseDomainToUiMapper(),
            coreModule.resourceProvider
        ),
        coreModule.resourceProvider
    )

    private fun getInteractor() = VersesInteractor.Base(
        getRepository(),
        BaseVersesDataToDomainMapper(BaseVerseDataToDomainMapper()),
        coreModule.chapterCache,
        booksModule.repository(),
        coreModule.bookCache,
        coreModule.scrollPositionCache
    )

    private fun getRepository(): VersesRepository {
        val mapper = ToVerseMapper.Base()
        return VersesRepository.Base(
            if (useMocks)
                VersesCloudDataSource.Mock(coreModule.resourceProvider, coreModule.gson)
            else
                VersesCloudDataSource.Base(
                    coreModule.language,
                    VersesCloudDataSource.English(
                        coreModule.makeService(VersesService::class.java),
                        coreModule.gson
                    ),
                    VersesCloudDataSource.Russian(coreModule.resourceProvider, coreModule.gson)
                ),
            VersesCacheDataSource.Base(coreModule.realmProvider, VerseDataToDbMapper.Base()),
            VersesCloudMapper.Base(mapper),
            VersesCacheMapper.Base(mapper),
            coreModule.chapterCache,
            coreModule.bookCache
        )
    }
}