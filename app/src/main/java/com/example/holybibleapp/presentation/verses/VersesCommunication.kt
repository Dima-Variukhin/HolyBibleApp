package com.example.holybibleapp.presentation.verses

import com.example.holybibleapp.core.Communication
import com.example.holybibleapp.presentation.chapters.ChapterUi
import com.example.holybibleapp.presentation.chapters.ChaptersCommunication

interface VersesCommunication : Communication<List<VerseUi>> {
    class Base : Communication.Base<List<VerseUi>>(), VersesCommunication
}