package com.vt.beagle_ui.beagle_components.widget

import android.view.View
import br.com.zup.beagle.android.context.Bind
import br.com.zup.beagle.android.utils.observeBindChanges
import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.android.widget.WidgetView
import br.com.zup.beagle.annotation.RegisterWidget
import com.vt.beagle_ui.beagle_components.custom_components.ItemRowDivider

@RegisterWidget
class ItemRowDivider(
    private val dividerColor: Bind<String>,
    private val dividerHeight: Bind<Int>
): WidgetView() {
    override fun buildView(rootView: RootView): View {
        return ItemRowDivider(rootView.getContext()).apply {
            observeBindChanges(rootView, this, dividerColor) {
                it?.let {
                    setColor(it)
                }
            }

            observeBindChanges(rootView, this, dividerHeight) {
                it?.let {
                    height = it
                }
            }
        }
    }
}
