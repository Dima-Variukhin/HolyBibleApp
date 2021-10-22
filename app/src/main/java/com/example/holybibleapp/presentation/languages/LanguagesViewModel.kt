package com.example.holybibleapp.presentation.languages

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.holybibleapp.NavigationCommunication
import com.example.holybibleapp.R
import com.example.holybibleapp.core.ResourceProvider
import com.example.holybibleapp.presentation.BaseViewModel
import com.example.holybibleapp.presentation.Navigator

class LanguagesViewModel(
    private val communication: LanguagesCommunication,
    private val language: Language,
    private val navigationCommunication: NavigationCommunication,
    private val navigator: LanguagesNavigator,
    private val resourceProvider: ResourceProvider
) : BaseViewModel(resourceProvider) {

    override fun getTitleResId() = R.string.choose_language

    fun observeLanguage(owner: LifecycleOwner, observer: Observer<LanguageUi>) {
        communication.observe(owner, observer)
    }

    fun chooseEnglish() {
        language.chooseEnglish()
        navigate()
    }

    fun chooseRussian() {
        language.chooseRussian()
        navigate()
    }

    private fun navigate() = navigationCommunication.map(navigator.nextFromLanguages())

    fun init() {
        val choice = when {
            language.isChosenRussian() -> LanguageChoice.RUSSIAN
            language.isChosenEnglish() -> LanguageChoice.ENGLISH
            else -> LanguageChoice.NONE
        }
        communication.map(
            LanguageUi(
                choice,
                resourceProvider.getString(R.string.english),
                resourceProvider.getString(R.string.russian)
            )
        )
    }

    fun showBackIcon(): Boolean = with(navigator) {
        saveLanguagesScreen()
        return canGoBack()
    }
}