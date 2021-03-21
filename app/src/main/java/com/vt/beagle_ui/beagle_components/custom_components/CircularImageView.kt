package com.vt.beagle_ui.beagle_components.custom_components

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import com.vt.beagle_ui.R
import com.vt.beagle_ui.extensions.loadGlide
import kotlinx.android.synthetic.main.layout_circular_image_view.view.*

class CircularImageView(context: Context) : LinearLayout(context) {

    init {
        View.inflate(context, R.layout.layout_circular_image_view, this)
    }

    fun setImageView(remoteUrl: String) {
        imageView.loadGlide(url = remoteUrl, isCircleCrop = true, isCached = true)
    }
}