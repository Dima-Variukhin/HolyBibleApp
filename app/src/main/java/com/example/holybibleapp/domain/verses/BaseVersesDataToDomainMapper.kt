package com.example.holybibleapp.domain.verses

import com.example.holybibleapp.core.BuildString
import com.example.holybibleapp.data.verses.VerseData
import com.example.holybibleapp.data.verses.VerseDataToDomainMapper
import com.example.holybibleapp.data.verses.VersesDataToDomainMapper

class BaseVersesDataToDomainMapper(
    private val mapper: VerseDataToDomainMapper<VerseDomain>
) : VersesDataToDomainMapper<VersesDomain>() {
    override fun map(data: Triple<List<VerseData>, BuildString, Int>) =
        VersesDomain.Success(
            data.first.map { verseData ->
                verseData.map(mapper)
            }, data.second, data.third
        )

    override fun map(e: Exception) = VersesDomain.Fail(errorType(e))
}