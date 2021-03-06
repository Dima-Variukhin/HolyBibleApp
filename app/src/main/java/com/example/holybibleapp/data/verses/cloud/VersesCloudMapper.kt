package com.example.holybibleapp.data.verses.cloud

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.data.verses.ToVerseMapper
import com.example.holybibleapp.data.verses.VerseData

interface VersesCloudMapper : Abstract.Mapper.Data<List<VerseCloud>, List<VerseData>> {
    class Base(private val mapper: ToVerseMapper<VerseData>) : VersesCloudMapper {
        override fun map(data: List<VerseCloud>): List<VerseData> = data.map { verse ->
            verse.map(mapper)
        }
    }
}