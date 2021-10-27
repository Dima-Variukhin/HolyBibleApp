package com.example.holybibleapp

import org.junit.Test

class CollapseFirstTestamentTest : BaseTest() {


    @Test
    fun test() {
        BooksPage().run {
            firstBookOldTestament.checkVisible()
            oldTestament.tap()
            firstBookOldTestament.checkDoesntExist()
        }
    }
}