package com.example.holybibleapp.presentation

import com.example.holybibleapp.NavigationCommunication

interface NavigateForward {

    fun nextScreen(navigationCommunication: NavigationCommunication)
}