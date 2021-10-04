package com.example.holybibleapp.presentation

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.holybibleapp.NavigationCommunication
import com.example.holybibleapp.core.PreferencesProvider
import com.example.holybibleapp.core.Read
import com.example.holybibleapp.core.Save
import com.example.holybibleapp.presentation.books.BookCache
import com.example.holybibleapp.presentation.books.BooksFragment
import com.example.holybibleapp.presentation.books.BooksNavigator
import com.example.holybibleapp.presentation.chapters.ChaptersFragment
import com.example.holybibleapp.presentation.chapters.ChaptersNavigator
import com.example.holybibleapp.presentation.verses.VersesFragment
import com.example.holybibleapp.presentation.verses.VersesNavigator

interface Navigator : Save<Int>, Read<Int>, MainNavigator, BooksNavigator, ChaptersNavigator,
    VersesNavigator {
    class Base(preferencesProvider: PreferencesProvider) : Navigator {

        private val screens = listOf(
            BooksFragment::class.java,
            ChaptersFragment::class.java,
            VersesFragment::class.java
        )

        private val sharedPreferences =
            preferencesProvider.provideSharedPreferences(NAVIGATOR_FILE_NAME)

        override fun save(data: Int) =
            sharedPreferences.edit().putInt(CURRENT_SCREEN_KEY, data).apply()

        override fun read() = sharedPreferences.getInt(CURRENT_SCREEN_KEY, 0)

        override fun getFragment(id: Int): BaseFragment = screens[id].newInstance()

        override fun nextScreen(navigationCommunication: NavigationCommunication) =
            navigationCommunication.map(read() + 1)

        override fun saveBooksScreen() = save(BOOKS_SCREEN)
        override fun saveChaptersScreen() = save(CHAPTERS_SCREEN)
        override fun saveVersesScreen() = save(VERSES_SCREEN)

        private companion object {
            const val NAVIGATOR_FILE_NAME = "navigation"
            const val CURRENT_SCREEN_KEY = "screenId"

            const val BOOKS_SCREEN = 0
            const val CHAPTERS_SCREEN = 1
            const val VERSES_SCREEN = 2
        }
    }
}