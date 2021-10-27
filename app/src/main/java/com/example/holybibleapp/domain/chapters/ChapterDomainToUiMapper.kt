package com.example.holybibleapp.domain.chapters

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.presentation.chapters.ChapterId
import com.example.holybibleapp.presentation.chapters.ChapterUi

interface ChapterDomainToUiMapper<T> : Abstract.Mapper.Data<ChapterId, T> {
}