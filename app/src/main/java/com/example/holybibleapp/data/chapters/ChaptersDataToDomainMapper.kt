package com.example.holybibleapp.data.chapters

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.TextMapper

abstract class ChaptersDataToDomainMapper<T> :
    Abstract.Mapper.DataToDomain.Base<Pair<List<ChapterData>, Abstract.Object<Unit, TextMapper>>, T>()