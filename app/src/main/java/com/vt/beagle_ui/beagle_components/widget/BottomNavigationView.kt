package com.vt.beagle_ui.beagle_components.widget

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.android.widget.WidgetView
import br.com.zup.beagle.annotation.RegisterWidget
import com.vt.beagle_ui.beagle_components.custom_components.BottomNavigationView

@RegisterWidget
class BottomNavigationView(
    private val menuItems: ArrayList<Array<String>>,
    private val selectedColor: String? = "#3596EC",
    private val unselectedColor: String? = "#788793"
): WidgetView() {
    override fun buildView(rootView: RootView): View {
        return BottomNavigationView(rootView.getContext()).apply {
            setupMenu(menuItems, selectedColor, unselectedColor, rootView.getContext() as AppCompatActivity)
        }
    }
}