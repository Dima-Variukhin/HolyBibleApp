package com.example.holybibleapp.core

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.LocaleList
import androidx.annotation.StringRes
import com.example.holybibleapp.presentation.languages.ChooseLanguage
import java.io.BufferedReader
import java.util.*

interface ResourceProvider : RawResourceReader, PreferencesProvider, ChooseLanguage {

    fun getString(@StringRes id: Int): String
    fun getString(@StringRes id: Int, vararg args: Any): String

    class Base(private var context: Context) : ResourceProvider {
        override fun getString(id: Int) = context.getString(id)
        override fun getString(id: Int, vararg args: Any) = context.getString(id, *args)

        override fun readText(id: Int) = context.resources.openRawResource(id).bufferedReader()
            .use(BufferedReader::readText)

        override fun provideSharedPreferences(name: String): SharedPreferences =
            context.getSharedPreferences(name, Context.MODE_PRIVATE)

        override fun chooseEnglish() = changeLanguage("en", Locale.ENGLISH)
        override fun chooseRussian() = changeLanguage("ru", Locale("ru", "RU"))

        private fun changeLanguage(lang: String, locale: Locale) {
            val conf = context.resources.configuration
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                conf.setLocales(LocaleList.forLanguageTags(lang))
            } else {
                conf.setLocale(locale)
            }
            context = context.createConfigurationContext(conf)
        }
    }
}