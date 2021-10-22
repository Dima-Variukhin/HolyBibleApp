package com.example.holybibleapp.presentation.books


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.holybibleapp.R
import com.example.holybibleapp.core.BibleApp
import com.example.holybibleapp.core.ClickListener
import com.example.holybibleapp.core.Retry
import com.example.holybibleapp.presentation.BaseFragment

class BooksFragment : BaseFragment<BooksViewModel>() {
    override fun viewModelClass() = BooksViewModel::class.java
    override fun getTitle() = getString(R.string.app_name)
    override fun showBackIcon() = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = BooksAdapter(object : Retry {
            override fun tryAgain() = viewModel.fetchBooks()
        },
            object : BooksAdapter.CollapseListener {
                override fun collapseOrExpand(id: Int) = viewModel.collapseOrExpand(id)
            },
            object : ClickListener<BookUi> {
                override fun click(item: BookUi) = item.open(viewModel)
            })

        recyclerView?.adapter = adapter
        viewModel.observe(this)
        {
            adapter.update(it)
        }
        //в навигатор кладется айди бук скрин
        viewModel.init()
    }

    override fun onPause() {
        viewModel.save()
        super.onPause()
    }
}