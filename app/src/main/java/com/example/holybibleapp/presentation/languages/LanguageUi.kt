package com.example.holybibleapp.presentation.languages

import com.example.holybibleapp.core.TextMapper

class LanguageUi(
    private val choice: LanguageChoice,
    private val englishTitle: String,
    private val russianTitle: String
) {

    fun showEnglishTitle(textMapper: TextMapper) = textMapper.map(englishTitle)
    fun showRussianTitle(textMapper: TextMapper) = textMapper.map(russianTitle)
    fun handleChoice(russian: () -> Unit, english: () -> Unit, none: () -> Unit) {
        when (choice) {
            LanguageChoice.ENGLISH -> english()
            LanguageChoice.RUSSIAN -> russian()
            LanguageChoice.NONE -> none()
        }
    }
}

enum class LanguageChoice {
    ENGLISH,
    RUSSIAN,
    NONE
}