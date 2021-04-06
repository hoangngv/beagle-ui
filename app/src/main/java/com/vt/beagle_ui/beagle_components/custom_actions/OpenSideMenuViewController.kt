package com.vt.beagle_ui.beagle_components.custom_actions

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import br.com.zup.beagle.android.action.Action
import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.annotation.RegisterAction
import com.vt.beagle_ui.base.BaseDialogFragment
import com.vt.beagle_ui.base.BaseNavigationDrawerFragment

@RegisterAction
data class OpenSideMenuViewController(val url: String) : Action {
    override fun execute(rootView: RootView, origin: View) {
        val fragmentManager = (rootView.getContext() as AppCompatActivity).supportFragmentManager
        val drawerFragment = BaseNavigationDrawerFragment.newInstance(url)
        fragmentManager.beginTransaction().add(drawerFragment, url).show(drawerFragment).commit()
    }
}