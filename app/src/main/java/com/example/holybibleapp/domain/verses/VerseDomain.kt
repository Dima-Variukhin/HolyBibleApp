package com.example.holybibleapp.domain.verses

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.presentation.verses.VerseUi

data class VerseDomain(private val id: Int, private val text: String) :
    Abstract.Object<VerseUi, VerseDomainToUiMapper> {
    override fun map(mapper: VerseDomainToUiMapper) = mapper.map(id, text)
}