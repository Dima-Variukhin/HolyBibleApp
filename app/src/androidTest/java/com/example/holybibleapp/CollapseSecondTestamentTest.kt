package com.example.holybibleapp

import org.junit.Test

class CollapseSecondTestamentTest : BaseTest() {
    @Test
    fun test() {
        BooksPage().run {
            firstBookNewTestament.checkVisible()
            newTestament.tap()
            firstBookNewTestament.checkDoesntExist()
        }
    }
}