package com.example.holybibleapp.presentation.languages

import com.example.holybibleapp.core.Read
import com.example.holybibleapp.presentation.NavigateBack
import com.example.holybibleapp.presentation.NavigateForward

interface LanguagesNavigator : NavigateForward, Read<Int>, NavigateBack {
    fun saveLanguagesScreen()
    fun nextFromLanguages(): Int
}