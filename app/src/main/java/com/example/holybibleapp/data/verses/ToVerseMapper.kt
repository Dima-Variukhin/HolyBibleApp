package com.example.holybibleapp.data.verses

import com.example.holybibleapp.core.Abstract

interface ToVerseMapper : Abstract.Mapper {
    fun map(id: Int, verseId: Int, text: String): VerseData

    class Base : ToVerseMapper {
        override fun map(id: Int, verseId: Int, text: String) = VerseData(id, verseId, text)
    }
}