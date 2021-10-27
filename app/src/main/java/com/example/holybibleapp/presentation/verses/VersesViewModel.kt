package com.example.holybibleapp.presentation.verses

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.example.holybibleapp.core.ResourceProvider
import com.example.holybibleapp.domain.verses.VersesDomainToUiMapper
import com.example.holybibleapp.domain.verses.VersesInteractor
import com.example.holybibleapp.presentation.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VersesViewModel(
    private val navigator: VersesNavigator,
    private val interactor: VersesInteractor,
    private val communication: VersesCommunication,
    private val mapper: VersesDomainToUiMapper<VersesUi>,
    resourceProvider: ResourceProvider
) : BaseViewModel(resourceProvider) {
    fun init() {
        navigator.saveVersesScreen()
        fetchVerses()
    }

    fun fetchVerses() {
        communication.map(VersesUi.Base(listOf(VerseUi.Progress), getTitle()))
        viewModelScope.launch(Dispatchers.IO) {
            val list = interactor.fetchVerses()
            val ui = list.map(mapper)
            withContext(Dispatchers.Main) {
                communication.map(ui)
            }
        }
    }

    fun observeVerses(owner: LifecycleOwner, observer: Observer<VersesUi>) {
        communication.observe(owner, observer)
    }
}
