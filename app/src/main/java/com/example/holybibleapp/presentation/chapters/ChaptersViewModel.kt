package com.example.holybibleapp.presentation.chapters

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.example.holybibleapp.NavigationCommunication
import com.example.holybibleapp.core.*
import com.example.holybibleapp.domain.chapters.ChaptersDomainToUiMapper
import com.example.holybibleapp.domain.chapters.ChaptersInteractor
import com.example.holybibleapp.presentation.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChaptersViewModel(
    private val chaptersInteractor: ChaptersInteractor,
    private val chaptersCommunication: ChaptersCommunication,
    private val chaptersMapper: ChaptersDomainToUiMapper<ChaptersUi>,
    private val navigator: ChaptersNavigator,
    private val chapterCache: Save<Int>,
    private val navigationCommunication: NavigationCommunication,
    resourceProvider: ResourceProvider
) : BaseViewModel(resourceProvider), Show {

    init {
        Log.d("jsc91", "chapters newInstance")
    }

    fun observeChapters(owner: LifecycleOwner, observer: Observer<ChaptersUi>) {
        chaptersCommunication.observe(owner, observer)
    }

    fun fetchChapters() {
        chaptersCommunication.map(
            ChaptersUi.Base(listOf(ChapterUi.Progress),
                object : Abstract.Object<Unit, TextMapper> {
                    override fun map(mapper: TextMapper) = mapper.map(getTitle())
                })
        )
        viewModelScope.launch(Dispatchers.IO) {
            val chapters = chaptersInteractor.showChapters()
            val chaptersUi = chapters.map(chaptersMapper)
            withContext(Dispatchers.Main) {
                chaptersCommunication.map(chaptersUi)
            }
        }
    }

    fun init() {
        navigator.saveChaptersScreen()
        fetchChapters()
    }

    override fun open(id: Int) {
        chapterCache.save(id)
        navigator.nextScreen(navigationCommunication)
    }

    override fun scrollPosition() = chaptersInteractor.scrollPosition()
    override fun saveScrollPosition(position: Int) = chaptersInteractor.saveScrollPosition(position)
}
