package com.example.holybibleapp.data.verses

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.BuildString

abstract class VersesDataToDomainMapper<T> :
    Abstract.Mapper.DataToDomain.Base<Triple<List<VerseData>, BuildString, Int>, T>()