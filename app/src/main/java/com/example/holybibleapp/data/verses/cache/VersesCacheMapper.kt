package com.example.holybibleapp.data.verses.cache

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.data.verses.ToVerseMapper
import com.example.holybibleapp.data.verses.VerseData

interface VersesCacheMapper : Abstract.Mapper.Data<List<VerseDb>, List<VerseData>> {
    class Base(private val mapper: ToVerseMapper) : VersesCacheMapper {
        override fun map(data: List<VerseDb>) = data.map { verseDb ->
            verseDb.map(mapper)
        }
    }
}