package com.vt.beagle_ui.beagle_components.custom_widgets

import android.view.View
import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.android.widget.WidgetView
import br.com.zup.beagle.annotation.RegisterWidget
import com.vt.beagle_ui.beagle_components.custom_widget_view.PieChart

@RegisterWidget
class PieChart(val dataset: ArrayList<Array<String>>): WidgetView() {
    override fun buildView(rootView: RootView): View {
        return PieChart(rootView.getContext()).apply {
            setupPieChart(dataset)
        }
    }
}