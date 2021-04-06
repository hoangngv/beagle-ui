package com.vt.beagle_ui.beagle_components.custom_actions

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.zup.beagle.android.action.Action
import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.annotation.RegisterAction
import com.vt.beagle_ui.base.BaseBottomSheetDialogFragment
import com.vt.beagle_ui.base.BaseDialogFragment

@RegisterAction
data class DismissDialogAction(val endpoint: String): Action {
    override fun execute(rootView: RootView, origin: View) {
        val fragmentManager = (rootView.getContext() as AppCompatActivity).supportFragmentManager
        val dialogFragment = fragmentManager.findFragmentByTag(endpoint)
        if (dialogFragment is BaseDialogFragment) dialogFragment.dismiss()
        else if (dialogFragment is BaseBottomSheetDialogFragment) dialogFragment.dismiss()
    }
}