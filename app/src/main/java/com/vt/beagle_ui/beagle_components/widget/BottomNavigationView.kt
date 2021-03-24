package com.vt.beagle_ui.beagle_components.widget

import android.app.Activity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.android.widget.WidgetView
import br.com.zup.beagle.annotation.RegisterWidget
import com.vt.beagle_ui.beagle_components.custom_components.BottomNavigationView

@RegisterWidget
class BottomNavigationView(
    private val menuItems: ArrayList<Array<String>>
): WidgetView() {
    override fun buildView(rootView: RootView): View {
        return BottomNavigationView(rootView.getContext()).apply {
            setupMenu(menuItems, rootView.getContext() as AppCompatActivity)
        }
    }
}