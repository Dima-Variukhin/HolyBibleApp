package com.example.holybibleapp.domain.verses

import com.example.holybibleapp.core.BuildString
import com.example.holybibleapp.data.verses.VerseData
import com.example.holybibleapp.data.verses.VerseDataToDomainMapper
import com.example.holybibleapp.data.verses.VersesDataToDomainMapper

class BaseVersesDataToDomainMapper(
    private val mapper: VerseDataToDomainMapper<VerseDomain>
) : VersesDataToDomainMapper<VersesDomain>() {
    override fun map(data: Triple<List<VerseData>, BuildString, Pair<Int, Boolean>>): VersesDomain.Success {
        //добавляем все базовые элементы в лист
        val list = mutableListOf<VerseDomain>()
        list.addAll(data.first.map { verseData -> verseData.map(mapper) })
        if (!data.third.second)
            list.add(VerseDomain.Next)
        return VersesDomain.Success(list, data.second, data.third.first)
    }

    override fun map(e: Exception) = VersesDomain.Fail(errorType(e))
}