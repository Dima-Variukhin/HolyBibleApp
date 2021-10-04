package com.example.holybibleapp.domain.verses

import com.example.holybibleapp.data.verses.VerseDataToDomainMapper
import com.example.holybibleapp.data.verses.VersesDataToDomainMapper

class BaseVerseDataToDomainMapper : VerseDataToDomainMapper {
    override fun map(verseId: Int, text: String) = VerseDomain(verseId, text)
}