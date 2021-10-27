package com.example.holybibleapp.presentation.chapters

import com.example.holybibleapp.core.Communication

interface ChaptersCommunication : Communication<ChaptersUi> {
    class Base : Communication.Base<ChaptersUi>(), ChaptersCommunication
}