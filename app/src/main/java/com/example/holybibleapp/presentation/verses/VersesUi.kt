package com.example.holybibleapp.presentation.verses

import com.example.holybibleapp.core.Abstract

data class VersesUi(private val list: List<VerseUi>) : Abstract.Object<Unit, VersesCommunication> {
    override fun map(mapper: VersesCommunication) = mapper.map(list)
}