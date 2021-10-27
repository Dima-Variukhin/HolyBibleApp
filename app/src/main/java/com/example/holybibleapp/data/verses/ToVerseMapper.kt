package com.example.holybibleapp.data.verses


interface ToVerseMapper<T> {
    fun map(id: Int, verseId: Int, text: String): T

    class Base : ToVerseMapper<VerseData> {
        override fun map(id: Int, verseId: Int, text: String) = VerseData.Base(id, verseId, text)
    }
}