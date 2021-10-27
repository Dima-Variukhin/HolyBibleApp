package com.example.holybibleapp.presentation.languages

import com.example.holybibleapp.core.TextMapper

interface LanguageUi {
    fun map(
        englishMapper: TextMapper,
        russianMapper: TextMapper,
        russian: () -> Unit,
        english: () -> Unit,
        none: () -> Unit
    )

    class Base(
        private val choice: LanguageChoice,
        private val englishTitle: String,
        private val russianTitle: String
    ) : LanguageUi {
        override fun map(
            englishMapper: TextMapper,
            russianMapper: TextMapper,
            russian: () -> Unit,
            english: () -> Unit,
            none: () -> Unit
        ) {
            englishMapper.map(englishTitle)
            russianMapper.map(russianTitle)
            when (choice) {
                LanguageChoice.ENGLISH -> english()
                LanguageChoice.RUSSIAN -> russian()
                LanguageChoice.NONE -> none()
            }
        }
    }
}

enum class LanguageChoice {
    ENGLISH,
    RUSSIAN,
    NONE
}