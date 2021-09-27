package com.example.holybibleapp.data.chapters

import com.example.holybibleapp.R
import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.ResourceProvider
import com.example.holybibleapp.presentation.chapters.ChapterUi

interface ChapterIdToUiMapper : Abstract.Mapper {
    fun map(generatedId: Int, realId: Int): ChapterUi

    class Base(private val resourceProvider: ResourceProvider) : ChapterIdToUiMapper {
        override fun map(generatedId: Int, realId: Int) = ChapterUi.Base(
            generatedId, resourceProvider.getString(
                R.string.chapter_number, realId
            )
        )
    }
}