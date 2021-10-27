package com.example.holybibleapp.domain.verses


interface VerseDomain {
    fun <T> map(mapper: VerseDomainToUiMapper<T>): T

    data class Base(private val id: Int, private val text: String) :
        VerseDomain {
        override fun <T> map(mapper: VerseDomainToUiMapper<T>) = mapper.map(id, text)
    }
}