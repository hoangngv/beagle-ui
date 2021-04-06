package com.vt.beagle_ui.beagle_components.custom_actions

import android.view.View
import br.com.zup.beagle.android.action.Action
import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.annotation.RegisterAction
import com.vt.beagle_ui.model.BadgeModel
import com.vt.beagle_ui.utils.bus.SingleBus
import com.vt.beagle_ui.utils.bus.SingleBusKey

@RegisterAction
data class SetNotificationBadge(
    val number: Int,
    val badgeBackgroundColor: String,
    val badgeTextColor: String
) : Action {
    override fun execute(rootView: RootView, origin: View) {
        SingleBus.send(SingleBusKey.SET_BADGE_NUMBER, BadgeModel(number, badgeBackgroundColor, badgeTextColor))
    }
}