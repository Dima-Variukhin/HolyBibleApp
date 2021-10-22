package com.example.holybibleapp.core

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.example.holybibleapp.R


class CustomTextView : AppCompatTextView, TextMapper {
    //region constructors
    constructor(context: Context) : super(
        context
    )

    constructor(context: Context, attrs: AttributeSet) : super(
        context, attrs
    )

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    //endregion
    override fun map(data: String) = setText(data)
}

class CollapseView : AppCompatImageView, CollapseMapper {
    //region constructors
    constructor(context: Context) : super(
        context
    )

    constructor(context: Context, attrs: AttributeSet) : super(
        context, attrs
    )

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    //endregion
    override fun map(data: Boolean) {
        val iconId: Int = if (data) {
            R.drawable.ic_expand_more_24
        } else {
            R.drawable.ic_expand_less_24
        }
        setImageResource(iconId)
    }
}

class CustomRadioButton : androidx.appcompat.widget.AppCompatRadioButton, TextMapper {
    //region constructors
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
    //endregion

    override fun map(data: String) = setText(data)
}

interface TextMapper : Abstract.Mapper.Data<String, Unit>
interface CollapseMapper : Abstract.Mapper.Data<Boolean, Unit>