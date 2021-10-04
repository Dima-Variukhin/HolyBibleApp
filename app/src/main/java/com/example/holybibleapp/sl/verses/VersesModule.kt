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
import com.example.holybibleapp.domain.verses.VersesDomainToUiMapper
import com.example.holybibleapp.domain.verses.VersesInteractor
import com.example.holybibleapp.presentation.verses.BaseVerseDomainToUiMapper
import com.example.holybibleapp.presentation.verses.BaseVersesDomainToUiMapper
import com.example.holybibleapp.presentation.verses.VersesCommunication
import com.example.holybibleapp.presentation.verses.VersesViewModel
import com.example.holybibleapp.sl.core.BaseModule
import com.example.holybibleapp.sl.core.CoreModule

class VersesModule(private val coreModule: CoreModule) : BaseModule<VersesViewModel> {
    override fun getViewModel() = VersesViewModel(
        coreModule.navigator,
        getInteractor(),
        VersesCommunication.Base(),
        BaseVersesDomainToUiMapper(
            BaseVerseDomainToUiMapper(),
            coreModule.resourceProvider
        ),
        coreModule.bookCache,
        coreModule.chapterCache
    )

    private fun getInteractor() = VersesInteractor.Base(
        getRepository(),
        BaseVersesDataToDomainMapper(BaseVerseDataToDomainMapper())
    )

    private fun getRepository(): VersesRepository.Base {
        val mapper = ToVerseMapper.Base()
        return VersesRepository.Base(
            VersesCloudDataSource.Base(
                coreModule.makeService(VersesService::class.java),
                coreModule.gson
            ),
            VersesCacheDataSource.Base(coreModule.realmProvider, VerseDataToDbMapper.Base()),
            VersesCloudMapper.Base(mapper),
            VersesCacheMapper.Base(mapper),
            coreModule.chapterCache,
            coreModule.bookCache
        )
    }
}