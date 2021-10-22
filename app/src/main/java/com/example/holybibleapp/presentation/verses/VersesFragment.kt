package com.example.holybibleapp.presentation.verses

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.holybibleapp.core.BibleApp
import com.example.holybibleapp.core.Retry
import com.example.holybibleapp.presentation.BaseFragment

class VersesFragment : BaseFragment<VersesViewModel>() {

    override fun viewModelClass() = VersesViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = VersesAdapter(object : Retry {
            override fun tryAgain() = viewModel.fetchVerses()
        })

        viewModel.observeVerses(this) { (verses, title) ->
            adapter.update(verses)
            updateTitle(title)
        }
        recyclerView?.adapter = adapter

        viewModel.init()
    }
}