package com.example.holybibleapp.presentation

import androidx.fragment.app.Fragment
import com.example.holybibleapp.NavigationCommunication
import com.example.holybibleapp.core.Read

interface MainNavigator : Read<Int>, NavigateBack {
    fun getFragment(id: Int): BaseFragment<*>
    fun showLanguagesFragment(communication: NavigationCommunication)
}

interface NavigateBack {
    fun canGoBack(): Boolean
    fun navigateBack(navigationCommunication: NavigationCommunication)
}