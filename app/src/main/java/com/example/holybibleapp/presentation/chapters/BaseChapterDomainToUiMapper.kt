package com.example.holybibleapp.presentation.chapters

import com.example.holybibleapp.data.chapters.ChapterIdToUiMapper
import com.example.holybibleapp.domain.chapters.ChapterDomainToUiMapper

class BaseChapterDomainToUiMapper(private val mapper: ChapterIdToUiMapper) :
    ChapterDomainToUiMapper {
    override fun map(data: ChapterId) = data.map(mapper)
}
