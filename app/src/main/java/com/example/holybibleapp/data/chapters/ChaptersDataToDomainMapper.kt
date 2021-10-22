package com.example.holybibleapp.data.chapters

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.domain.chapters.ChaptersDomain
import java.lang.Exception

abstract class ChaptersDataToDomainMapper<T> :
    Abstract.Mapper.DataToDomain.Base<Pair<List<ChapterData>, String>, T>()