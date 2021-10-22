package com.example.holybibleapp.presentation.verses

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.Communication
import com.example.holybibleapp.presentation.chapters.ChapterUi
import com.example.holybibleapp.presentation.chapters.ChaptersCommunication

interface VersesCommunication : Communication<Pair<List<VerseUi>, String>>, Abstract.Mapper {
    class Base : Communication.Base<Pair<List<VerseUi>, String>>(), VersesCommunication
}