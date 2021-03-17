package com.vt.beagle_ui.beagle_components.actions

import android.view.View
import android.widget.Toast
import br.com.zup.beagle.android.action.Action
import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.annotation.RegisterAction

@RegisterAction
data class ToastAction(val msg: String? = null) : Action {
    override fun execute(rootView: RootView, origin: View) {
        Toast.makeText(rootView.getContext(), msg, Toast.LENGTH_SHORT).show()
    }
}