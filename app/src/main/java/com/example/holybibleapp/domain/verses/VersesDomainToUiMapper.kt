package com.example.holybibleapp.domain.verses

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.BuildString
import com.example.holybibleapp.core.ResourceProvider

abstract class VersesDomainToUiMapper<T>(resourceProvider: ResourceProvider) :
    Abstract.Mapper.DomainToUi.Base<Triple<List<VerseDomain>, BuildString, Int>, T>(resourceProvider)