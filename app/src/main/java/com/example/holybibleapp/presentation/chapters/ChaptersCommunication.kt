package com.example.holybibleapp.presentation.chapters

import com.example.holybibleapp.core.Communication

interface ChaptersCommunication : Communication<Pair<List<ChapterUi>, String>> {
    class Base : Communication.Base<Pair<List<ChapterUi>, String>>(), ChaptersCommunication
}