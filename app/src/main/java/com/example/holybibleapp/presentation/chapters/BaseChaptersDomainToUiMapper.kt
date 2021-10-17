package com.example.holybibleapp.presentation.chapters

import com.example.holybibleapp.core.ErrorType
import com.example.holybibleapp.core.ResourceProvider
import com.example.holybibleapp.domain.chapters.ChapterDomain
import com.example.holybibleapp.domain.chapters.ChapterDomainToUiMapper
import com.example.holybibleapp.domain.chapters.ChaptersDomainToUiMapper

class BaseChaptersDomainToUiMapper(
    private val mapper: ChapterDomainToUiMapper<ChapterUi>,
    resourceProvider: ResourceProvider
) : ChaptersDomainToUiMapper<ChaptersUi>(resourceProvider) {
    override fun map(data: List<ChapterDomain>) = ChaptersUi.Base(data.map { chapterDomain ->
        chapterDomain.map(mapper)
    })

    override fun map(errorType: ErrorType) =
        ChaptersUi.Base(listOf(ChapterUi.Fail(errorMessage(errorType))))
}