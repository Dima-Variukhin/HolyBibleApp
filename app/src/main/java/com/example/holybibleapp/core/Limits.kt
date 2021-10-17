package com.example.holybibleapp.core

interface Limits : Min, Max

interface Min {
    fun min(): Int
}

interface Max {
    fun max(): Int
}