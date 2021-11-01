package com.example.holybibleapp.presentation.verses

import android.os.Bundle
import android.view.View
import com.example.holybibleapp.core.ClickListener
import com.example.holybibleapp.core.Retry
import com.example.holybibleapp.presentation.BaseFragment

class VersesFragment : BaseFragment<VersesViewModel>() {

    override fun viewModelClass() = VersesViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = VersesAdapter(object : Retry {
            override fun tryAgain() = viewModel.fetchVerses()
        },
            object : ClickListener<VerseUi> {
                override fun click(item: VerseUi) = viewModel.showNextChapterVerses(item)
            })

        viewModel.observeVerses(this) { ui ->
            ui.map(adapter, title())
            scrollTo()
        }
        setAdapter(adapter)

        viewModel.init()
    }
}