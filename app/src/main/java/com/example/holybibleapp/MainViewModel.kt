package com.example.holybibleapp

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.holybibleapp.presentation.MainNavigator

class MainViewModel(
    private val navigator: MainNavigator,
    private val communication: NavigationCommunication
) : ViewModel() {
    fun init() {
        communication.map(navigator.read())
    }

    fun observe(owner: LifecycleOwner, observer: Observer<Int>) {
        communication.observe(owner, observer)
    }

    fun navigateBack(): Boolean {
        val currentScreen = navigator.read()
        val exit = currentScreen == 0
        val newScreen = currentScreen - 1
        if (newScreen >= 0) {
            communication.map(newScreen)
        }
        return exit
    }

    fun getFragment(id: Int): Fragment {
        return navigator.getFragment(id)
    }
}

