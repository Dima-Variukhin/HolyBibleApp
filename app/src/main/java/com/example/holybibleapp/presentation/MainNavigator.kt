package com.example.holybibleapp.presentation

import androidx.fragment.app.Fragment
import com.example.holybibleapp.core.Read

interface MainNavigator : Read<Int> {
     fun getFragment(id: Int): Fragment
}