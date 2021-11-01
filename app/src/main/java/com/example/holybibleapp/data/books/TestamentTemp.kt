package com.example.holybibleapp.data.books

import com.example.holybibleapp.core.Match
import com.example.holybibleapp.core.Save

interface TestamentTemp : Match<String>, Save<String> {
    fun isEmpty(): Boolean
    class Base : TestamentTemp {
        private var temp: String = ""
        override fun save(data: String) {
            temp = data
        }

        override fun matches(arg: String) = temp == arg
        override fun isEmpty() = temp.isEmpty()
    }
}