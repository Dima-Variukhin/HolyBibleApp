package com.example.holybibleapp.data.verses

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.domain.verses.VersesDomain

abstract class VersesDataToDomainMapper<T> :
    Abstract.Mapper.DataToDomain.Base<Pair<List<VerseData>, String>, T>()