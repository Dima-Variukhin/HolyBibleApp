package com.example.holybibleapp.data.chapters

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.domain.chapters.ChapterDomain
import com.example.holybibleapp.presentation.chapters.ChapterId

interface ChapterDataToDomainMapper : Abstract.Mapper.Data<ChapterId, ChapterDomain>
