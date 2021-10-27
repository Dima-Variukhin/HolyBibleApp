package com.example.holybibleapp.presentation.verses

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.Communication

interface VersesCommunication : Communication<VersesUi>, Abstract.Mapper {
    class Base : Communication.Base<VersesUi>(), VersesCommunication
}