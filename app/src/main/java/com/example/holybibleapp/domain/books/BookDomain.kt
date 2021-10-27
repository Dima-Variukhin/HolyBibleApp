package com.example.holybibleapp.domain.books

sealed class BookDomain {

    abstract fun <T> map(mapper: BookDomainToUiMapper<T>): T

    data class Base(
        private val id: Int,
        private val name: String
    ) : BookDomain() {
        override fun <T> map(mapper: BookDomainToUiMapper<T>) = mapper.map(id, name)
    }

    data class Testament(private val type: TestamentType) : BookDomain() {
        override fun <T> map(mapper: BookDomainToUiMapper<T>) = type.map(mapper)
    }
}
