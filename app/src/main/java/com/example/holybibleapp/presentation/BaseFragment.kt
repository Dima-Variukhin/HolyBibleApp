package com.example.holybibleapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.holybibleapp.MainActivity
import com.example.holybibleapp.R
import com.example.holybibleapp.core.Matcher

abstract class BaseFragment<T : BaseViewModel> : Fragment(), Matcher<String> {
    protected lateinit var viewModel: T
    protected var recyclerView: RecyclerView? = null
    protected abstract fun viewModelClass(): Class<T>
    protected open fun showBackIcon() = true

    // переопределение разметки для каждого фрагмента
    protected open fun layoutResId() = R.layout.fragment_main

    override fun matches(arg: String) = name() == arg
    fun name(): String = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (requireActivity() as MainActivity).getViewModel(viewModelClass(), this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutResId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(
            showBackIcon()
        )
        updateTitle(viewModel.getTitle())
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView?.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

    protected fun updateTitle(title: String) = requireActivity().setTitle(title)
}