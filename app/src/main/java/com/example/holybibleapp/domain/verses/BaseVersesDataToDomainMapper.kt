package com.example.holybibleapp.domain.verses

import com.example.holybibleapp.data.verses.VerseData
import com.example.holybibleapp.data.verses.VerseDataToDomainMapper
import com.example.holybibleapp.data.verses.VersesDataToDomainMapper
import java.lang.Exception

class BaseVersesDataToDomainMapper(
    private val mapper: VerseDataToDomainMapper
) : VersesDataToDomainMapper() {
    override fun map(data: List<VerseData>) = VersesDomain.Success(data.map { verseData ->
        verseData.map(mapper)
    })

    override fun map(e: Exception) = VersesDomain.Fail(errorType(e))
}