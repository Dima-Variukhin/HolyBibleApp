package com.example.holybibleapp.presentation.chapters

import com.example.holybibleapp.data.chapters.ChapterIdToUiMapper
import com.example.holybibleapp.domain.chapters.ChapterDomainToUiMapper

class BaseChapterDomainToUiMapper(private val mapper: ChapterIdToUiMapper<ChapterUi>) :
    ChapterDomainToUiMapper<ChapterUi> {
    override fun map(data: ChapterId) = data.map(mapper)
}
