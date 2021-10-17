package com.example.holybibleapp.domain.chapters

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.ResourceProvider
import com.example.holybibleapp.presentation.chapters.ChaptersUi

abstract class ChaptersDomainToUiMapper<T>(resourceProvider: ResourceProvider) :
    Abstract.Mapper.DomainToUi.Base<List<ChapterDomain>, T>(resourceProvider) {
}