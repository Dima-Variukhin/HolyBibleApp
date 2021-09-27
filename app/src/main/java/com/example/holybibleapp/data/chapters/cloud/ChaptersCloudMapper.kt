package com.example.holybibleapp.data.chapters.cloud

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.data.chapters.ChapterData
import com.example.holybibleapp.data.chapters.ToChapterMapper

interface ChaptersCloudMapper : Abstract.Mapper {

    fun map(chapters: List<ChapterCloud>, bookId: Int): List<ChapterData>

    class Base(private val mapper: ToChapterMapper) : ChaptersCloudMapper {
        override fun map(chapters: List<ChapterCloud>, bookId: Int) = chapters.map { chapterCloud ->
            chapterCloud.map(mapper)
        }
    }
}
