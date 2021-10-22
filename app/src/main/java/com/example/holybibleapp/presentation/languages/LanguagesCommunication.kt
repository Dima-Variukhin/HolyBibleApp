package com.example.holybibleapp.presentation.languages

import com.example.holybibleapp.core.Communication

interface LanguagesCommunication : Communication<LanguageUi> {
    class Base : Communication.Base<LanguageUi>(), LanguagesCommunication
}