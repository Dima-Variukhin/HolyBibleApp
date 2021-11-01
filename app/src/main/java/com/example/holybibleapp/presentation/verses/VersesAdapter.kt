package com.example.holybibleapp.presentation.verses

import android.view.View
import android.view.ViewGroup
import com.example.holybibleapp.R
import com.example.holybibleapp.core.*

class VersesAdapter(
    private val retry: Retry,
    private val clickListener: ClickListener<VerseUi>
) : BaseAdapter<VerseUi, BaseViewHolder<VerseUi>>() {

    override fun getItemViewType(position: Int) = when (list[position]) {
        is VerseUi.Base -> 0
        is VerseUi.Fail -> 1
        is VerseUi.Progress -> 2
        is VerseUi.Next -> 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        0 -> VerseViewHolder.Base(R.layout.verses_layout.makeView(parent))
        1 -> BaseViewHolder.Fail(R.layout.fail_fullscreen.makeView(parent), retry)
        3 -> VerseViewHolder.Next(R.layout.next_layout.makeView(parent), clickListener)
        else -> BaseViewHolder.FullScreenProgress(R.layout.progress_fullscreen.makeView(parent))
    }

    abstract class VerseViewHolder(view: View) : BaseViewHolder<VerseUi>(view) {
        class Base(view: View) : VerseViewHolder(view) {
            private val textView = itemView.findViewById<CustomTextView>(R.id.textView)
            override fun bind(item: VerseUi) = item.map(textView)
        }

        class Next(view: View, private val clickListener: ClickListener<VerseUi>) :
            VerseViewHolder(view) {
            private val nextButton = itemView.findViewById<CustomButton>(R.id.nextButton)
            override fun bind(item: VerseUi) {
                item.map(nextButton)
                nextButton.setOnClickListener {
                    clickListener.click(item)
                }
            }
        }
    }
}
