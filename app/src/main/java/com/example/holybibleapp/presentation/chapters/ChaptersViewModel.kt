package com.example.holybibleapp.presentation.chapters

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.holybibleapp.NavigationCommunication
import com.example.holybibleapp.core.Read
import com.example.holybibleapp.core.Save
import com.example.holybibleapp.domain.chapters.ChaptersDomainToUiMapper
import com.example.holybibleapp.domain.chapters.ChaptersInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChaptersViewModel(
    private val chaptersInteractor: ChaptersInteractor,
    private val chaptersCommunication: ChaptersCommunication,
    private val chaptersMapper: ChaptersDomainToUiMapper,
    private val navigator: ChaptersNavigator,
    private val bookCache: Read<Pair<Int, String>>,
    private val chapterCache: Save<Int>,
    private val navigationCommunication: NavigationCommunication
) : ViewModel(), Show {

    init {
        Log.d("jsc91", "chapters newInstance")

    }

    fun observeChapters(owner: LifecycleOwner, observer: Observer<List<ChapterUi>>) {
        chaptersCommunication.observe(owner, observer)
    }

    fun fetchChapters() {
        chaptersCommunication.map(listOf(ChapterUi.Progress))
        viewModelScope.launch(Dispatchers.IO) {
            val chapters = chaptersInteractor.showChapters()
            val chaptersUi = chapters.map(chaptersMapper)
            withContext(Dispatchers.Main) {
                chaptersUi.map(chaptersCommunication)
            }
        }
    }

    fun init() {
        navigator.saveChaptersScreen()
        fetchChapters()
    }

    fun getBookName() = bookCache.read().second
    override fun show(id: Int) {
        chapterCache.save(id)
        navigator.nextScreen(navigationCommunication)
    }
}

interface Show {
    fun show(id: Int)
}