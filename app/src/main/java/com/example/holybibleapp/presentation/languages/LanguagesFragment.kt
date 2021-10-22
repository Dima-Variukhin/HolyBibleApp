package com.example.holybibleapp.presentation.languages

import android.graphics.drawable.Animatable2
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.Drawable
import android.media.Image
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.RadioButton
import androidx.fragment.app.viewModels
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.example.holybibleapp.R
import com.example.holybibleapp.core.BibleApp
import com.example.holybibleapp.presentation.BaseFragment

class LanguagesFragment : BaseFragment<LanguagesViewModel>() {
    override fun getTitle() = getString(R.string.choose_language)
    override fun showBackIcon() = viewModel.showBackIcon()
    override fun layoutResId() = R.layout.fragment_languages

    override fun viewModelClass() = LanguagesViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val englishRadioButton = view.findViewById<RadioButton>(R.id.englishRadioButton)
        val russianRadioButton = view.findViewById<RadioButton>(R.id.russianRadioButton)
        val english = view.findViewById<View>(R.id.english)
        val russian = view.findViewById<View>(R.id.russian)

        val chooseEnglish: (v: View) -> Unit = {
            viewModel.chooseEnglish()
        }

        english.setOnClickListener {
            viewModel.chooseEnglish()
        }

        russian.setOnClickListener {
            viewModel.chooseRussian()
        }

        viewModel.observeLanguage(this) {
            if (it.isChosenEnglish()) {
                englishRadioButton.isChecked = true
            } else if (it.isChosenRussian()) {
                russianRadioButton.isChecked = true
            }
        }

        viewModel.getChosenLanguage()
        viewModel.init()
    }
}