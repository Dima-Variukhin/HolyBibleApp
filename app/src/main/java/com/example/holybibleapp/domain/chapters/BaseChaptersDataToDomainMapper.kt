package com.example.holybibleapp.domain.chapters

import com.example.holybibleapp.data.chapters.ChapterData
import com.example.holybibleapp.data.chapters.ChapterDataToDomainMapper
import com.example.holybibleapp.data.chapters.ChaptersDataToDomainMapper
import java.lang.Exception

class BaseChaptersDataToDomainMapper(private val mapper: ChapterDataToDomainMapper<ChapterDomain>) :
    ChaptersDataToDomainMapper<ChaptersDomain>() {
    override fun map(e: Exception) = ChaptersDomain.Fail(errorType(e))

    override fun map(data: List<ChapterData>) = ChaptersDomain.Success(data.map { chapterData ->
        chapterData.map(mapper)
    })
}