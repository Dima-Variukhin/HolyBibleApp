package com.example.holybibleapp.data.verses.cache

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.data.verses.ToVerseMapper
import com.example.holybibleapp.data.verses.VerseData
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class VerseDb : RealmObject(), Abstract.Object<VerseData, ToVerseMapper> {
    @PrimaryKey
    var id: Int = -1
    var verseId: Int = -1
    var text: String = ""
    override fun map(mapper: ToVerseMapper) = VerseData(id, verseId, text)
}