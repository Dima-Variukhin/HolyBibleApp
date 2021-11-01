package com.example.holybibleapp.core

interface Match<T> {
    fun matches(arg: T): Boolean
}