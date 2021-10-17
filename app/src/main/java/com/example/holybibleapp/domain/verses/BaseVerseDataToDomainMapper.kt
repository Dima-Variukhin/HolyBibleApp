package com.example.holybibleapp.domain.verses

import com.example.holybibleapp.data.verses.VerseDataToDomainMapper
import com.example.holybibleapp.data.verses.VersesDataToDomainMapper

class BaseVerseDataToDomainMapper : VerseDataToDomainMapper<VerseDomain> {
    override fun map(verseId: Int, text: String) = VerseDomain.Base(verseId, text)
}