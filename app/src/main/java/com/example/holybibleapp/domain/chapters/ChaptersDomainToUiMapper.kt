package com.example.holybibleapp.domain.chapters

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.ResourceProvider
import com.example.holybibleapp.core.TextMapper

abstract class ChaptersDomainToUiMapper<T>(resourceProvider: ResourceProvider) :
    Abstract.Mapper.DomainToUi.Base<Pair<List<ChapterDomain>, Abstract.Object<Unit, TextMapper>>, T>(
        resourceProvider
    ) {
}