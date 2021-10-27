package com.example.holybibleapp.presentation.verses

import com.example.holybibleapp.R
import com.example.holybibleapp.core.BuildString
import com.example.holybibleapp.core.ErrorType
import com.example.holybibleapp.core.ResourceProvider
import com.example.holybibleapp.domain.verses.VerseDomain
import com.example.holybibleapp.domain.verses.VerseDomainToUiMapper
import com.example.holybibleapp.domain.verses.VersesDomainToUiMapper

class BaseVersesDomainToUiMapper(
    private val mapper: VerseDomainToUiMapper<VerseUi>,
    private val resourceProvider: ResourceProvider
) : VersesDomainToUiMapper<VersesUi>(resourceProvider) {
    override fun map(data: Triple<List<VerseDomain>, BuildString, Int>) =
        VersesUi.Base(data.first.map { verse ->
            verse.map(mapper)
        }, data.second.build(resourceProvider, R.string.book_and_chapter, data.third))

    override fun map(errorType: ErrorType) =
        errorMessage(errorType).let { VersesUi.Base(listOf(VerseUi.Fail(it)), it) }
}