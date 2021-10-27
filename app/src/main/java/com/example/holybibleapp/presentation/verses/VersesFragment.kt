package com.example.holybibleapp.presentation.verses

import android.os.Bundle
import android.view.View
import com.example.holybibleapp.core.Retry
import com.example.holybibleapp.presentation.BaseFragment

class VersesFragment : BaseFragment<VersesViewModel>() {

    override fun viewModelClass() = VersesViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = VersesAdapter(object : Retry {
            override fun tryAgain() = viewModel.fetchVerses()
        })

        viewModel.observeVerses(this) { ui ->
            ui.map(adapter, title())
        }
        setAdapter(adapter)

        viewModel.init()
    }
}