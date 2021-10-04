package com.example.holybibleapp.data.verses

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.domain.verses.VerseDomain

interface VerseDataToDomainMapper : Abstract.Mapper {
    fun map(verseId: Int, text: String): VerseDomain
}