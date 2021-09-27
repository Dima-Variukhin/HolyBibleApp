package com.example.holybibleapp

import com.example.holybibleapp.core.Communication

interface NavigationCommunication : Communication<Int> {
    class Base : Communication.Base<Int>(), NavigationCommunication
}