package com.example.holybibleapp.data.verses

interface VerseDataToDomainMapper<T> {
    fun map(verseId: Int, text: String): T
}