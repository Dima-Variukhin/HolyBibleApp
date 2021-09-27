package com.example.holybibleapp.data.chapters.cache

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.data.chapters.ChapterData
import com.example.holybibleapp.data.chapters.ToChapterMapper

interface ChaptersCacheMapper : Abstract.Mapper.Data<List<ChapterDb>, List<ChapterData>> {

    class Base(private val mapper: ToChapterMapper) : ChaptersCacheMapper {
        override fun map(data: List<ChapterDb>) = data.map { chapterDb ->
            chapterDb.map(mapper)
        }
    }
}