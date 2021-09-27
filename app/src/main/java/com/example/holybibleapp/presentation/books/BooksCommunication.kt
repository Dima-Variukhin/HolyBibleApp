package com.example.holybibleapp.presentation.books

import com.example.holybibleapp.core.Communication

interface BooksCommunication : Communication<List<BookUi>> {

    class Base : Communication.Base<List<BookUi>>(), BooksCommunication
}