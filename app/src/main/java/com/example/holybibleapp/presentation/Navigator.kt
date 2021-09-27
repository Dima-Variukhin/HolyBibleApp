package com.example.holybibleapp.presentation

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.holybibleapp.NavigationCommunication
import com.example.holybibleapp.core.Read
import com.example.holybibleapp.core.Save
import com.example.holybibleapp.presentation.books.BooksFragment
import com.example.holybibleapp.presentation.books.BooksNavigator
import com.example.holybibleapp.presentation.chapters.ChaptersFragment
import com.example.holybibleapp.presentation.chapters.ChaptersNavigator

interface Navigator : Save<Int>, Read<Int>, MainNavigator, BooksNavigator, ChaptersNavigator {
    class Base(context: Context) : Navigator {

        private val list = listOf(
            object : FragmentGetter {
                override fun get(): Fragment {
                    return BooksFragment()
                }
            },
            object : FragmentGetter {
                override fun get(): Fragment {
                    return ChaptersFragment()
                }
            }
        )

        private val sharedPreferences =
            context.getSharedPreferences(NAVIGATOR_FILE_NAME, Context.MODE_PRIVATE)

        override fun save(data: Int) {
            sharedPreferences.edit().putInt(CURRENT_SCREEN_KEY, data).apply()
        }

        override fun read(): Int {
            return sharedPreferences.getInt(CURRENT_SCREEN_KEY, 0)
        }

        override fun getFragment(id: Int): Fragment {
            return list[id].get()
        }

        override fun saveBooksScreen() {
            save(BOOKS_SCREEN)
        }

        override fun nextScreen(navigationCommunication: NavigationCommunication) {
            navigationCommunication.map(read() + 1)
        }

        override fun saveChaptersScreen() {
            save(CHAPTERS_SCREEN)
        }

        private companion object {
            const val NAVIGATOR_FILE_NAME = "navigation"
            const val CURRENT_SCREEN_KEY = "screenId"
            const val BOOKS_SCREEN = 0
            const val CHAPTERS_SCREEN = 1

        }
    }
}

interface FragmentGetter {
    fun get(): Fragment
}