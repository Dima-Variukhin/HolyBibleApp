package com.example.holybibleapp.presentation.chapters

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.ErrorType
import com.example.holybibleapp.core.ResourceProvider
import com.example.holybibleapp.core.TextMapper
import com.example.holybibleapp.domain.chapters.ChapterDomain
import com.example.holybibleapp.domain.chapters.ChapterDomainToUiMapper
import com.example.holybibleapp.domain.chapters.ChaptersDomainToUiMapper

class BaseChaptersDomainToUiMapper(
    private val mapper: ChapterDomainToUiMapper<ChapterUi>,
    resourceProvider: ResourceProvider
) : ChaptersDomainToUiMapper<ChaptersUi>(resourceProvider) {
    override fun map(data: Pair<List<ChapterDomain>, Abstract.Object<Unit, TextMapper>>) =
        ChaptersUi.Base(data.first.map { chapterDomain ->
            chapterDomain.map(mapper)
        }, data.second)

    override fun map(errorType: ErrorType) = errorMessage(errorType).let { error ->
        ChaptersUi.Base(listOf(ChapterUi.Fail(error)), object : Abstract.Object<Unit, TextMapper> {
            override fun map(mapper: TextMapper) = mapper.map(error)
        })
    }
}