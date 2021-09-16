package com.example.holybibleapp.presentation

import androidx.recyclerview.widget.DiffUtil

class DiffUtilCallback(
    private val odlList: List<BookUi>,
    private val newList: List<BookUi>,
) : DiffUtil.Callback() {
    override fun getOldListSize() = odlList.size
    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        odlList[oldItemPosition].same(newList[newItemPosition])

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        odlList[oldItemPosition].sameContent(newList[newItemPosition])
}