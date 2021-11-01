package com.example.holybibleapp.presentation.languages

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.example.holybibleapp.R
import com.example.holybibleapp.core.CustomRadioButton
import com.example.holybibleapp.presentation.BaseFragment

class LanguagesFragment : BaseFragment<LanguagesViewModel>() {
    override fun showBackIcon() = viewModel.showBackIcon()
    override fun layoutResId() = R.layout.fragment_languages
    override fun viewModelClass() = LanguagesViewModel::class.java

    private lateinit var englishFlagView: ImageView
    private lateinit var russianFlagView: ImageView
    private lateinit var englishRadioButton: CustomRadioButton
    private lateinit var russianRadioButton: CustomRadioButton

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        englishRadioButton = view.findViewById(R.id.englishRadioButton)
        russianRadioButton = view.findViewById(R.id.russianRadioButton)
        val english = view.findViewById<View>(R.id.english)
        val russian = view.findViewById<View>(R.id.russian)
        englishFlagView = view.findViewById(R.id.engImageView)
        russianFlagView = view.findViewById(R.id.ruImageView)

        val chooseEnglish: (v: View) -> Unit = {
            viewModel.chooseEnglish()
        }

        val chooseRussian: (v: View) -> Unit = {
            viewModel.chooseRussian()
        }

        english.setOnClickListener(chooseEnglish)
        englishRadioButton.setOnClickListener(chooseEnglish)
        russian.setOnClickListener(chooseRussian)
        russianRadioButton.setOnClickListener(chooseRussian)

        fun russianChosen() {
            setStaticRussianFlag()
            russianRadioButton.isChecked = true
        }

        fun englishChosen() {
            setStaticEnglishFlag()
            englishRadioButton.isChecked = true
        }

        fun noLanguageChosen() {
            setStaticEnglishFlag()
            setStaticRussianFlag()
        }
        viewModel.observeLanguage(this) {
            it.map(
                englishRadioButton,
                russianRadioButton,
                ::russianChosen,
                ::englishChosen,
                ::noLanguageChosen
            )
        }
        viewModel.init()
    }

    private fun setStaticRussianFlag() = russianFlagView.setImageResource(R.drawable.russian_flag)
    private fun setStaticEnglishFlag() = englishFlagView.setImageResource(R.drawable.english_flag)
}