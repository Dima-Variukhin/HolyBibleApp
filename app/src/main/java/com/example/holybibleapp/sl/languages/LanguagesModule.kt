package com.example.holybibleapp.sl.languages

import com.example.holybibleapp.presentation.languages.Language
import com.example.holybibleapp.presentation.languages.LanguagesCommunication
import com.example.holybibleapp.presentation.languages.LanguagesViewModel
import com.example.holybibleapp.sl.core.BaseModule
import com.example.holybibleapp.sl.core.CoreModule

class LanguagesModule(private val coreModule: CoreModule) : BaseModule<LanguagesViewModel> {
    override fun getViewModel() = LanguagesViewModel(
        LanguagesCommunication.Base(),
        Language.Change(coreModule.language, coreModule.realmProvider, coreModule.resourceProvider),
        coreModule.navigatorCommunication,
        coreModule.navigator,
        coreModule.resourceProvider
    )
}
