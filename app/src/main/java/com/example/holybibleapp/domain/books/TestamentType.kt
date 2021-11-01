package com.example.holybibleapp.domain.books

import com.example.holybibleapp.core.Match

enum class TestamentType(private val id: Int) : Match<Int> {
    OLD(Int.MIN_VALUE),
    NEW(Int.MAX_VALUE);

    override fun matches(arg: Int) = id == arg
    fun <T> map(mapper: BookDomainToUiMapper<T>) = mapper.map(id, name)
}
