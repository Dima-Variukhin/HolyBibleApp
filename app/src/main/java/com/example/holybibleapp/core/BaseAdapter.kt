package com.example.holybibleapp.core

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.holybibleapp.R

abstract class BaseAdapter<E : ComparableTextMapper<E>, T : BaseViewHolder<E>> :
    RecyclerView.Adapter<T>(), ListMapper<E> {
    protected val list = ArrayList<E>()

    override fun map(data: List<E>) {
        val diffCallback = DiffUtilCallback(list, data)
        val result = DiffUtil.calculateDiff(diffCallback)
        list.clear()
        list.addAll(data)
        result.dispatchUpdatesTo(this)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: T, position: Int) = holder.bind(list[position])

    protected fun Int.makeView(parent: ViewGroup) =
        LayoutInflater.from(parent.context).inflate(this, parent, false)

}

interface ComparableTextMapper<T : ComparableTextMapper<T>> : Abstract.Object<Unit, TextMapper>,
    Comparing<T>

abstract class BaseViewHolder<E : ComparableTextMapper<E>>(view: View) :
    RecyclerView.ViewHolder(view) {
    open fun bind(item: E) {
    }

    class FullScreenProgress<E : ComparableTextMapper<E>>(view: View) : BaseViewHolder<E>(view)

    class Fail<E : ComparableTextMapper<E>>(
        view: View,
        private val retry: Retry
    ) : BaseViewHolder<E>(view) {
        private val message = itemView.findViewById<CustomTextView>(R.id.messageTextView)
        private val button = itemView.findViewById<View>(R.id.tryAgainButton)
        override fun bind(item: E) {
            item.map(message)
            button.setOnClickListener {
                retry.tryAgain()
            }
        }
    }
}

interface ClickListener<T> {
    fun click(item: T)
}
