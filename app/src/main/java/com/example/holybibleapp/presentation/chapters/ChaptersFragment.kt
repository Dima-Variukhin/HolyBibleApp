package com.example.holybibleapp.presentation.chapters

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.example.holybibleapp.core.BibleApp
import com.example.holybibleapp.core.Retry
import com.example.holybibleapp.presentation.BaseFragment

class ChaptersFragment : BaseFragment() {
    private val viewModelFactory by lazy {
        (requireActivity().application as BibleApp).chaptersFactory()
    }
    private val viewModel by viewModels<ChaptersViewModel> { viewModelFactory }

    override fun getTitle() = viewModel.getBookName()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ChaptersAdapter(object : Retry {
            override fun tryAgain() = viewModel.fetchChapters()
        })
        viewModel.observeChapters(this) { adapter.update(it) }
        recyclerView?.adapter = adapter

        viewModel.init()
    }

    init {
        Log.d("jsc91", "chaptersFragment()")

    }
}