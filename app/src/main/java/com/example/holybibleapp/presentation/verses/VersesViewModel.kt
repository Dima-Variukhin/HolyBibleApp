package com.example.holybibleapp.presentation.verses

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.holybibleapp.core.Read
import com.example.holybibleapp.domain.verses.VersesDomainToUiMapper
import com.example.holybibleapp.domain.verses.VersesInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VersesViewModel(
    private val navigator: VersesNavigator,
    private val interactor: VersesInteractor,
    private val communication: VersesCommunication,
    private val mapper: VersesDomainToUiMapper,
    private val bookCache: Read<Pair<Int, String>>,
    private val chapterCache: Read<Int>
) : ViewModel() {
    fun getTitle(): String {
        return "${bookCache.read().second} CH. ${chapterCache.read()}"
    }

    fun init() {
        navigator.saveVersesScreen()
        fetchVerses()
    }

    fun fetchVerses() {
        communication.map(listOf(VerseUi.Progress))
        viewModelScope.launch(Dispatchers.IO) {
            val list = interactor.fetchVerses()
            val uiList = list.map(mapper)
            withContext(Dispatchers.Main) {
                uiList.map(communication)
            }
        }
    }

    fun observeVerses(owner: LifecycleOwner, observer: Observer<List<VerseUi>>) {
        communication.observe(owner, observer)
    }
}
