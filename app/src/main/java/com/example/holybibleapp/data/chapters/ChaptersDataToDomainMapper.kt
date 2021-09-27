package com.example.holybibleapp.data.chapters

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.domain.chapters.ChaptersDomain
import java.lang.Exception

abstract class ChaptersDataToDomainMapper :
    Abstract.Mapper.DataToDomain.Base<List<ChapterData>, ChaptersDomain>()