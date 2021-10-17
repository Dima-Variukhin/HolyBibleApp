package com.example.holybibleapp.domain.chapters

import com.example.holybibleapp.presentation.chapters.ChapterId

interface ChapterDomain {
    fun <T> map(mapper: ChapterDomainToUiMapper<T>): T

    data class Base(private val id: ChapterId) : ChapterDomain {

        override fun <T> map(mapper: ChapterDomainToUiMapper<T>) = mapper.map(id)

    }
}