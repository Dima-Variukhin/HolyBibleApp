package com.example.holybibleapp.presentation

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import com.example.holybibleapp.R
import com.example.holybibleapp.core.ResourceProvider

abstract class BaseViewModel(private val resourceProvider: ResourceProvider) : ViewModel() {
    fun getTitle(): String = resourceProvider.getString(getTitleResId())

    @StringRes
    open fun getTitleResId(): Int = R.string.loading
}