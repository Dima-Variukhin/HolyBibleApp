package com.example.holybibleapp.presentation.verses

import com.example.holybibleapp.core.ErrorType
import com.example.holybibleapp.core.ResourceProvider
import com.example.holybibleapp.domain.verses.VerseDomain
import com.example.holybibleapp.domain.verses.VerseDomainToUiMapper
import com.example.holybibleapp.domain.verses.VersesDomainToUiMapper

class BaseVersesDomainToUiMapper(
    private val mapper: VerseDomainToUiMapper<VerseUi>,
    resourceProvider: ResourceProvider
) : VersesDomainToUiMapper<VersesUi>(resourceProvider) {
    override fun map(data: List<VerseDomain>) = VersesUi.Base(data.map { verse ->
        verse.map(mapper)
    })

    override fun map(errorType: ErrorType) =
        VersesUi.Base(listOf(VerseUi.Fail(errorMessage(errorType))))
}