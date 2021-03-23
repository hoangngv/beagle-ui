package com.vt.beagle_ui.beagle_components.custom_components

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.vt.beagle_ui.R
import kotlinx.android.synthetic.main.item_row_divider.view.*


class ItemRowDivider constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1
): ConstraintLayout(context, attrs, defStyleAttr){

    init {
        View.inflate(context, R.layout.item_row_divider, this)
        invalidate()
        requestLayout()
    }

    fun setColor(color: String) {
        divider.setBackgroundColor(Color.parseColor(color))
    }

    fun setHeight(height: Int) {
        val params: ViewGroup.LayoutParams = divider.layoutParams
        params.height = height
        divider.layoutParams = params
    }

    fun setMargin(top: Int, bottom: Int, left: Int, right: Int) {
        val params: LinearLayout.LayoutParams = divider.layoutParams as LinearLayout.LayoutParams
        params.setMargins(left, top, right, bottom)
        divider.layoutParams = params
    }
}