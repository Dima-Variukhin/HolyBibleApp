package com.example.holybibleapp.presentation.verses

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.holybibleapp.core.BibleApp
import com.example.holybibleapp.core.Retry
import com.example.holybibleapp.presentation.BaseFragment
import com.example.holybibleapp.presentation.chapters.ChaptersAdapter

class VersesFragment : BaseFragment() {
    private val viewModelFactory by lazy {
        (requireActivity().application as BibleApp).versesFactory()
    }
    private val viewModel by viewModels<VersesViewModel> { viewModelFactory }

    override fun getTitle() = viewModel.getTitle()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = VersesAdapter(object : Retry {
            override fun tryAgain() = viewModel.fetchVerses()
        })

        viewModel.observeVerses(this) {
            adapter.update(it)
        }
        recyclerView?.adapter = adapter

        viewModel.init()
    }
}