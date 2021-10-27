package com.example.holybibleapp.core

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.holybibleapp.BuildConfig.USE_MOCKS
import com.example.holybibleapp.sl.books.BooksModule
import com.example.holybibleapp.sl.chapters.ChaptersModule
import com.example.holybibleapp.sl.core.CoreModule
import com.example.holybibleapp.sl.core.ViewModelsFactory
import com.example.holybibleapp.sl.languages.LanguagesModule
import com.example.holybibleapp.sl.verses.VersesModule

class BibleApp : Application() {
    private val coreModule = CoreModule(USE_MOCKS)

    private val booksModule by lazy {
        BooksModule(coreModule, USE_MOCKS)
    }

    val factory by lazy {
        ViewModelsFactory(
            coreModule,
            booksModule,
            ChaptersModule(coreModule, booksModule, USE_MOCKS),
            VersesModule(coreModule, booksModule, USE_MOCKS),
            LanguagesModule(coreModule)
        )
    }

    override fun onCreate() {
        super.onCreate()
        coreModule.init(this)
    }

    fun <T : ViewModel> getViewModel(modelClass: Class<T>, owner: ViewModelStoreOwner): T =
        ViewModelProvider(owner, factory).get(modelClass)
}
