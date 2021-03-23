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
    private val dividerHeight: Bind<Int>,
    private val leftMargin: Int? = null,
    private val topMargin: Int? = null,
    private val rightMargin: Int? = null,
    private val bottomMargin: Int? = null,
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

            observeBindChanges(rootView, this, dividerColor) {
                it?.let {
                    setColor(it)
                }
            }

            if (leftMargin != null && topMargin != null && rightMargin != null && bottomMargin != null) {
                setMargin(topMargin, bottomMargin, leftMargin, rightMargin)
            }
        }
    }
}
