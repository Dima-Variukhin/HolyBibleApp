package com.example.holybibleapp.presentation.chapters

import com.example.holybibleapp.core.Communication

interface ChaptersCommunication : Communication<List<ChapterUi>> {
    class Base : Communication.Base<List<ChapterUi>>(), ChaptersCommunication
}