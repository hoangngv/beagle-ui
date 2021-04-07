package com.vt.beagle_ui.beagle_components.custom_widgets

import android.view.View
import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.android.widget.WidgetView
import br.com.zup.beagle.annotation.RegisterWidget
import com.vt.beagle_ui.beagle_components.custom_widget_view.CircularImageView

@RegisterWidget
class CircularImageView(private val remoteUrl: String): WidgetView() {
    override fun buildView(rootView: RootView): View {
        return CircularImageView(rootView.getContext()).apply {
            setImageView(remoteUrl)
        }
    }
}