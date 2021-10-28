package com.example.holybibleapp.data.verses.cloud

interface VersesScrollPositionCache {
    fun saveVersesScrollPosition(position: Int)
    fun versesScrollPosition(): Int
}