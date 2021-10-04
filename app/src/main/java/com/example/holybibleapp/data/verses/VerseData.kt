package com.example.holybibleapp.data.verses

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.DbWrapper
import com.example.holybibleapp.data.verses.cache.VerseDataToDbMapper
import com.example.holybibleapp.data.verses.cache.VerseDb
import com.example.holybibleapp.domain.verses.VerseDomain

class VerseData(
    private val id: Int,
    private val verseId: Int,
    private val text: String
) : Abstract.Object.ToDb<VerseDb, VerseDataToDbMapper>,
    Abstract.Object<VerseDomain, VerseDataToDomainMapper> {
    override fun map(mapper: VerseDataToDomainMapper) = mapper.map(verseId, text)
    override fun mapBy(mapper: VerseDataToDbMapper, db: DbWrapper<VerseDb>) =
        mapper.mapToDb(id, verseId, text, db)
}