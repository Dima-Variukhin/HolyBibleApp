package com.example.holybibleapp.presentation.books


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.holybibleapp.R
import com.example.holybibleapp.core.BibleApp
import com.example.holybibleapp.core.Retry
import com.example.holybibleapp.presentation.BaseFragment

class BooksFragment : BaseFragment() {

    private val viewModelFactory by lazy {
        (requireActivity().application as BibleApp).booksFactory()
    }

    private val viewModel by activityViewModels<BooksViewModel> { viewModelFactory }

    override fun getTitle() = getString(R.string.app_name)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = BooksAdapter(object : Retry {
            override fun tryAgain() = viewModel.fetchBooks()
        },
            object : BooksAdapter.CollapseListener {
                override fun collapseOrExpand(id: Int) = viewModel.collapseOrExpand(id)
            },
            object : BooksAdapter.BookListener {
                override fun showBook(id: Int, name: String) = viewModel.showBook(id, name)
            })

        recyclerView?.adapter = adapter
        viewModel.observer(this)
        {
            adapter.update(it)
        }
        //в навигатор кладется айди бук скрин
        viewModel.init()
    }

    init {
        Log.d("jsc91", "booksfragment()")

    }
}