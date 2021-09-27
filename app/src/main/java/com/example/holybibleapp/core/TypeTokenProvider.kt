package com.example.holybibleapp.core

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class TypeTokenProvider<T> {

    fun provideType(): Type {
        return object : TypeToken<T>() {}.type
    }
}