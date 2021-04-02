package com.vt.beagle_ui.beagle_components.actions

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.zup.beagle.android.action.Action
import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.annotation.RegisterAction
import com.vt.beagle_ui.base.BaseBottomSheetDialogFragment

@RegisterAction
data class ShowBottomSheetAction(private val endpoint: String): Action {
    override fun execute(rootView: RootView, origin: View) {
        val bottomSheetFragment = BaseBottomSheetDialogFragment.newInstance(endpoint)
        bottomSheetFragment.show((rootView.getContext() as AppCompatActivity).supportFragmentManager, bottomSheetFragment.tag)
    }
}