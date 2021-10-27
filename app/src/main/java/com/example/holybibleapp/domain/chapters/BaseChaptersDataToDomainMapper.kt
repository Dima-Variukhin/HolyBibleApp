package com.example.holybibleapp.domain.chapters

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.TextMapper
import com.example.holybibleapp.data.chapters.ChapterData
import com.example.holybibleapp.data.chapters.ChapterDataToDomainMapper
import com.example.holybibleapp.data.chapters.ChaptersDataToDomainMapper

class BaseChaptersDataToDomainMapper(private val mapper: ChapterDataToDomainMapper<ChapterDomain>) :
    ChaptersDataToDomainMapper<ChaptersDomain>() {
    override fun map(e: Exception) = ChaptersDomain.Fail(errorType(e))

    override fun map(data: Pair<List<ChapterData>, Abstract.Object<Unit, TextMapper>>) =
        ChaptersDomain.Success(data.first.map { chapterData ->
            chapterData.map(mapper)
        }, data.second)
}