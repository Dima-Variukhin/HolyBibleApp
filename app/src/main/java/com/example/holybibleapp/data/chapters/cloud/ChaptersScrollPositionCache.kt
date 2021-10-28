package com.example.holybibleapp.data.chapters.cloud

interface ChaptersScrollPositionCache {
    fun saveChaptersScrollPosition(position: Int)
    fun chaptersScrollPosition(): Int
}