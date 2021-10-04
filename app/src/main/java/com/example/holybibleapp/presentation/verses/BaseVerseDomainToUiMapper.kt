package com.example.holybibleapp.presentation.verses

import com.example.holybibleapp.domain.verses.VerseDomainToUiMapper

class BaseVerseDomainToUiMapper : VerseDomainToUiMapper {
    override fun map(id: Int, text: String) = VerseUi.Base("$id. $text")
}