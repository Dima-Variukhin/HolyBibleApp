package com.example.holybibleapp.domain.verses

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.ResourceProvider
import com.example.holybibleapp.presentation.verses.VersesUi

abstract class VersesDomainToUiMapper<T>(resourceProvider: ResourceProvider) :
    Abstract.Mapper.DomainToUi.Base<List<VerseDomain>, T>(resourceProvider)