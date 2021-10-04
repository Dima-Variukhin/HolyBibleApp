package com.example.holybibleapp.core

import android.content.SharedPreferences

interface PreferencesProvider {
    fun provideSharedPreferences(name: String): SharedPreferences
}