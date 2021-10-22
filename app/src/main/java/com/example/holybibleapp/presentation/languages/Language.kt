package com.example.holybibleapp.presentation.languages

import com.example.holybibleapp.core.PreferencesProvider
import com.example.holybibleapp.core.Read
import com.example.holybibleapp.core.ResourceProvider

interface Language : ChooseLanguage, ChosenLanguage, Read<Int> {

    abstract class Abstract(preferencesProvider: PreferencesProvider) : Language {

        private val sharedPreferences by lazy {
            preferencesProvider.provideSharedPreferences(getLanguageFileName())
        }

        protected abstract fun getLanguageFileName(): String
        protected abstract fun getLanguageKey(): String

        override fun read() = sharedPreferences.getInt(getLanguageKey(), -1)
        override fun chooseEnglish() = save(ENGLISH)
        override fun chooseRussian() = save(RUSSIAN)

        override fun isChosenEnglish() = read() == ENGLISH
        override fun isChosenRussian() = read() == RUSSIAN

        private companion object {
            const val ENGLISH = 0
            const val RUSSIAN = 1
        }

        private fun save(language: Int) {
            sharedPreferences.edit().putInt(getLanguageKey(), language).apply()
        }
    }

    class Base(resourceProvider: ResourceProvider) : Abstract(resourceProvider) {
        override fun getLanguageFileName() = LANGUAGES_FILE_NAME
        override fun getLanguageKey() = LANGUAGES_KEY

        private companion object {
            const val LANGUAGES_FILE_NAME = "languagesFileName"
            const val LANGUAGES_KEY = "languagesKey"
        }
    }

    class Mock(resourceProvider: PreferencesProvider) : Abstract(resourceProvider) {
        override fun getLanguageFileName() = LANGUAGES_FILE_NAME
        override fun getLanguageKey() = LANGUAGES_KEY

        private companion object {
            const val LANGUAGES_FILE_NAME = "mockLanguagesFileName"
            const val LANGUAGES_KEY = "mockLanguagesKey"
        }
    }

    class Change(
        private val language: Language,
        private val chooseLanguage: ChooseLanguage,
        private val chooseLanguagesResources: ChooseLanguage
    ) : Language {
        override fun chooseEnglish() {
            //сохранение в sharedPref
            language.chooseEnglish()
            //смена языка у реалма
            chooseLanguage.chooseEnglish()
            chooseLanguagesResources.chooseEnglish()
        }

        override fun chooseRussian() {
            //сохранение в sharedPref
            language.chooseRussian()
            //смена языка у реалма
            chooseLanguage.chooseRussian()
            chooseLanguagesResources.chooseRussian()
        }

        override fun isChosenEnglish() = language.isChosenEnglish()
        override fun isChosenRussian() = language.isChosenRussian()
        override fun read() = language.read()

    }
}

interface ChosenLanguage {
    fun isChosenEnglish(): Boolean
    fun isChosenRussian(): Boolean
}

interface ChooseLanguage {
    fun chooseEnglish()
    fun chooseRussian()
}