package com.vt.beagle_ui.beagle_components.actions

import android.view.View
import androidx.core.content.ContextCompat.startActivity
import br.com.zup.beagle.android.action.Action
import br.com.zup.beagle.android.utils.newServerDrivenIntent
import br.com.zup.beagle.android.view.ScreenRequest
import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.annotation.RegisterAction
import com.vt.beagle_ui.ui.AppBeagleActivity

@RegisterAction
class NavigateAction(val destination : String) : Action {
    override fun execute(rootView: RootView, origin: View) {
        val intent = rootView.getContext().newServerDrivenIntent<AppBeagleActivity>(ScreenRequest(destination))
        startActivity(rootView.getContext(), intent, null)
    }
}