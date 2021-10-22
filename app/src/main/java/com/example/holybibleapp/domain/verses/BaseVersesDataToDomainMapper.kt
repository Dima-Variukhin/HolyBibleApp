package com.example.holybibleapp.domain.verses

import com.example.holybibleapp.data.verses.VerseData
import com.example.holybibleapp.data.verses.VerseDataToDomainMapper
import com.example.holybibleapp.data.verses.VersesDataToDomainMapper
import java.lang.Exception

class BaseVersesDataToDomainMapper(
    private val mapper: VerseDataToDomainMapper<VerseDomain>
) : VersesDataToDomainMapper<VersesDomain>() {
    override fun map(data: Pair<List<VerseData>, String>) =
        VersesDomain.Success(
            data.first.map { verseData ->
                verseData.map(mapper)
            }, data.second
        )

    override fun map(e: Exception) = VersesDomain.Fail(errorType(e))
}