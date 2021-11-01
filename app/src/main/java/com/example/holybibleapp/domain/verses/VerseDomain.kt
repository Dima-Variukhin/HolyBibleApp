package com.example.holybibleapp.domain.verses

import com.example.holybibleapp.core.Match


interface VerseDomain {
    fun <T> map(mapper: VerseDomainToUiMapper<T>): T

    data class Base(private val id: Int, private val text: String) : VerseDomain {
        override fun <T> map(mapper: VerseDomainToUiMapper<T>) = mapper.map(id, text)
    }

    object Next : VerseDomain, Match<Pair<Int, String>> {
        override fun <T> map(mapper: VerseDomainToUiMapper<T>): T = mapper.map(ID, TEXT)
        override fun matches(arg: Pair<Int, String>) =
            arg.first == ID && arg.second == TEXT

        private const val ID = -20
        private const val TEXT = "next chapter text"
    }
}