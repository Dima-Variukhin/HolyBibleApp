package com.example.holybibleapp.data.chapters

import com.example.holybibleapp.R
import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.ResourceProvider
import com.example.holybibleapp.presentation.chapters.ChapterUi

interface ChapterIdToUiMapper<T> {
    fun map(realId: Int): T

    class Base(private val resourceProvider: ResourceProvider) : ChapterIdToUiMapper<ChapterUi> {
        override fun map(realId: Int) = ChapterUi.Base(
            realId, resourceProvider.getString(
                R.string.chapter_number, realId
            )
        )
    }
}