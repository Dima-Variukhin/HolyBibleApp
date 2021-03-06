package com.example.holybibleapp.domain.chapters

import com.example.holybibleapp.data.chapters.ChapterDataToDomainMapper
import com.example.holybibleapp.presentation.chapters.ChapterId

class BaseChapterDataToDomainMapper : ChapterDataToDomainMapper<ChapterDomain> {
    override fun map(data: ChapterId) = ChapterDomain.Base(data)
}