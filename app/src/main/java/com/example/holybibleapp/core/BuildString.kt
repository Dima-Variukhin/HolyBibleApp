package com.example.holybibleapp.core

import androidx.annotation.StringRes

interface BuildString {
    fun build(resourceProvider: ResourceProvider, @StringRes id: Int, arg: Any): String

    class Empty : BuildString {
        override fun build(resourceProvider: ResourceProvider, id: Int, arg: Any) = ""
    }
}