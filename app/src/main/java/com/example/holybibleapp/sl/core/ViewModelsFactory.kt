package com.example.holybibleapp.sl.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.holybibleapp.MainViewModel
import com.example.holybibleapp.presentation.books.BooksViewModel
import com.example.holybibleapp.presentation.chapters.ChaptersViewModel
import com.example.holybibleapp.presentation.languages.LanguagesViewModel
import com.example.holybibleapp.presentation.verses.VersesViewModel
import com.example.holybibleapp.sl.books.BooksModule
import com.example.holybibleapp.sl.chapters.ChaptersModule
import com.example.holybibleapp.sl.languages.LanguagesModule
import com.example.holybibleapp.sl.verses.VersesModule
import java.lang.IllegalStateException

class ViewModelsFactory(
    private val coreModule: CoreModule,
    private val booksModule: BooksModule,
    private val chaptersModule: ChaptersModule,
    private val versesModule: VersesModule,
    private val languagesModule: LanguagesModule
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val module = when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> coreModule
            modelClass.isAssignableFrom(BooksViewModel::class.java) -> booksModule
            modelClass.isAssignableFrom(ChaptersViewModel::class.java) -> chaptersModule
            modelClass.isAssignableFrom(VersesViewModel::class.java) -> versesModule
            modelClass.isAssignableFrom(LanguagesViewModel::class.java) -> languagesModule
            else -> throw IllegalStateException("unknown viewModelClass $modelClass")
        }
        return module.getViewModel() as T
    }
}