package com.example.holybibleapp.presentation.verses

import com.example.holybibleapp.R
import com.example.holybibleapp.core.ResourceProvider
import com.example.holybibleapp.domain.verses.VerseDomain
import com.example.holybibleapp.domain.verses.VerseDomainToUiMapper

class BaseVerseDomainToUiMapper(private val resourceProvider: ResourceProvider) :
    VerseDomainToUiMapper<VerseUi> {

    override fun map(id: Int, text: String) = if (VerseDomain.Next.matches(Pair(id, text)))
        VerseUi.Next(resourceProvider.getString(R.string.next_chapter))
    else
        VerseUi.Base("$id. $text")
}