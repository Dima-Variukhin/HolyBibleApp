package com.example.holybibleapp.data.books

interface BooksScrollPositionCache {
    fun saveBooksScrollPosition(position: Int)
    fun booksScrollPosition(): Int
}