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
    override fun map(data: Pair<List<VerseDomain>, String>) =
        VersesUi.Base(data.first.map { verse ->
            verse.map(mapper)
        }, data.second)

    override fun map(errorType: ErrorType) =
        errorMessage(errorType).let { VersesUi.Base(listOf(VerseUi.Fail(it)), it) }
}