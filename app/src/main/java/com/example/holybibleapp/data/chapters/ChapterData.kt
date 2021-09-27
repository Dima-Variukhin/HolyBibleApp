package com.example.holybibleapp.data.chapters

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.DbWrapper
import com.example.holybibleapp.data.chapters.cache.ChapterDataToDbMapper
import com.example.holybibleapp.data.chapters.cache.ChapterDb
import com.example.holybibleapp.domain.chapters.ChapterDomain
import com.example.holybibleapp.presentation.chapters.ChapterId

class ChapterData(private val chapterId: ChapterId) :
    Abstract.Object.ToDb<ChapterDb, ChapterDataToDbMapper>,
    Abstract.Object<ChapterDomain, ChapterDataToDomainMapper> {

    override fun map(mapper: ChapterDataToDomainMapper) = mapper.map(chapterId)

    override fun mapBy(mapper: ChapterDataToDbMapper, db: DbWrapper<ChapterDb>) =
        mapper.mapToDb(chapterId, db)

}