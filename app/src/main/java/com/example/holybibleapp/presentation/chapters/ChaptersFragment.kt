package com.example.holybibleapp.presentation.chapters

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.example.holybibleapp.core.BibleApp
import com.example.holybibleapp.core.ClickListener
import com.example.holybibleapp.core.Retry
import com.example.holybibleapp.presentation.BaseFragment

class ChaptersFragment : BaseFragment<ChaptersViewModel>() {
    override fun viewModelClass() = ChaptersViewModel::class.java
    override fun getTitle() = viewModel.getBookName()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ChaptersAdapter(
            object : Retry {
                override fun tryAgain() = viewModel.fetchChapters()
            },
            object : ClickListener<ChapterUi> {
                override fun click(item: ChapterUi) = item.open(viewModel)
            })
        viewModel.observeChapters(this) { (chapters, title) ->
            adapter.update(chapters)
            updateTitle(title)
        }
        recyclerView?.adapter = adapter

        viewModel.init()
    }
}