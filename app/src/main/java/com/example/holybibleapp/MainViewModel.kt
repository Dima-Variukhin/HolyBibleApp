package com.example.holybibleapp

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.holybibleapp.presentation.MainNavigator

class MainViewModel(
    private val navigator: MainNavigator,
    private val communication: NavigationCommunication
) : ViewModel() {
    fun init(firstOpening: Boolean) {
        if (firstOpening)
            communication.map(navigator.read())
    }

    fun observe(owner: LifecycleOwner, observer: Observer<Int>) {
        communication.observe(owner, observer)
    }

    fun navigateBack(): Boolean {
        val canNavigateBack = navigator.canGoBack()
        if (canNavigateBack)
            navigator.navigateBack(communication)
        return !canNavigateBack
    }

    fun getFragment(id: Int) = navigator.getFragment(id)
    fun showLanguagesScreen() = navigator.showLanguagesFragment(communication)
}

