package com.example.holybibleapp.data.chapters.cloud

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.data.chapters.ChapterData
import com.example.holybibleapp.data.chapters.ToChapterMapper

interface ChaptersCloudMapper : Abstract.Mapper.Data<List<ChapterCloud>, List<ChapterData>> {

    class Base(private val mapper: ToChapterMapper<ChapterData>) : ChaptersCloudMapper {
        override fun map(data: List<ChapterCloud>) = data.map { chapterCloud ->
            chapterCloud.map(mapper)
        }
    }
}
