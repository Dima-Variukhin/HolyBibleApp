package com.example.holybibleapp.domain.verses

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.presentation.verses.VerseUi

interface VerseDomainToUiMapper<T> : Abstract.Mapper {
    fun map(id: Int, text: String): T
}