package com.example.holybibleapp.core

import android.content.Context
import com.example.holybibleapp.presentation.languages.ChooseLanguage
import com.example.holybibleapp.presentation.languages.ChosenLanguage
import io.realm.Realm
import io.realm.RealmConfiguration

interface RealmProvider : ChooseLanguage {

    fun provide(): Realm

    abstract class Abstract(
        context: Context,
        private val chosenLanguage: ChosenLanguage
    ) : RealmProvider {
        protected abstract fun fileNameEnglish(): String
        protected abstract fun fileNameRussian(): String

        private val config by lazy {
            getConfig(if (chosenLanguage.isChosenRussian()) fileNameRussian() else fileNameEnglish())
        }

        init {
            Realm.init(context)
            Realm.setDefaultConfiguration(config)
        }

        //дефолтный инстанс выбирается исходя из дефолтной конфигурации, которая меняется "на лету"
        override fun provide(): Realm = Realm.getDefaultInstance()

        override fun chooseEnglish() = Realm.setDefaultConfiguration(getConfig(fileNameEnglish()))
        override fun chooseRussian() = Realm.setDefaultConfiguration(getConfig(fileNameRussian()))

        private fun getConfig(name: String) = RealmConfiguration.Builder()
            .schemaVersion(SCHEMA_VERSION)
            .name(name)
            .build()

        private companion object {
            const val SCHEMA_VERSION = 0L
        }
    }

    class Base(
        context: Context,
        chosenLanguage: ChosenLanguage
    ) : RealmProvider.Abstract(context, chosenLanguage) {

        override fun fileNameEnglish() = FILE_NAME
        override fun fileNameRussian() = FILE_NAME_RU

        private companion object {
            const val FILE_NAME = "HolyBibleAppRealm"
            const val FILE_NAME_RU = "HolyBibleAppRealmRu"
        }
    }

    class Mock(
        context: Context,
        chosenLanguage: ChosenLanguage
    ) : RealmProvider.Abstract(context, chosenLanguage) {

        override fun fileNameEnglish() = FILE_NAME_MOCK
        override fun fileNameRussian() = FILE_NAME_RU_MOCK

        private companion object {
            const val FILE_NAME_MOCK = "HolyBibleAppRealmMock"
            const val FILE_NAME_RU_MOCK = "HolyBibleAppRealmRuMock"
        }
    }
}
