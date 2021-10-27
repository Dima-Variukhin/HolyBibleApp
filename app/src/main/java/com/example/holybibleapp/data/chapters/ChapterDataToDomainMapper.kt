package com.example.holybibleapp.data.chapters

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.domain.chapters.ChapterDomain
import com.example.holybibleapp.presentation.chapters.ChapterId

interface ChapterDataToDomainMapper<T> : Abstract.Mapper.Data<ChapterId, T>
