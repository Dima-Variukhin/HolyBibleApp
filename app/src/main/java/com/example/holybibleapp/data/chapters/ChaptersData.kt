package com.example.holybibleapp.data.chapters

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.domain.chapters.ChaptersDomain
import java.lang.Exception

sealed class ChaptersData : Abstract.Object<ChaptersDomain, ChaptersDataToDomainMapper> {

    data class Success(private val chapters: List<ChapterData>) : ChaptersData() {
        override fun map(mapper: ChaptersDataToDomainMapper) = mapper.map(chapters)

    }

    data class Fail(private val e: Exception) : ChaptersData(){
        override fun map(mapper: ChaptersDataToDomainMapper) = mapper.map(e)
    }
}