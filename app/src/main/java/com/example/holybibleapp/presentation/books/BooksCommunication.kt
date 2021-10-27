package com.example.holybibleapp.presentation.books

import com.example.holybibleapp.core.Communication

interface BooksCommunication : Communication<BooksUi> {
    class Base : Communication.Base<BooksUi>(), BooksCommunication
}