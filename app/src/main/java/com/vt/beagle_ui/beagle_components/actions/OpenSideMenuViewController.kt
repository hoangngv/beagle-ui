package com.vt.beagle_ui.beagle_components.actions

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.zup.beagle.android.action.Action
import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.annotation.RegisterAction
import com.vt.beagle_ui.R
import com.vt.beagle_ui.common.Gravity
import com.vt.beagle_ui.ui.FragmentDrawer

@RegisterAction
data class OpenSideMenuViewController(val url : String, val gravity: Gravity = Gravity.START) : Action {
    override fun execute(rootView: RootView, origin: View) {
        val fragmentManager = (rootView.getContext() as AppCompatActivity).supportFragmentManager
        val fragment = FragmentDrawer.newInstance(url, gravity)
//        fragmentManager.beginTransaction().add(R.id.fragment_container,fragment, "hihi").show(fragment).commitAllowingStateLoss()

        val anims = if(gravity == Gravity.END) {
             arrayOf(
                R.anim.slide_right_to_center, R.anim.slide_center_to_right, R.anim.slide_right_to_center, R.anim.slide_center_to_right
            )
        } else {
            arrayOf(
                R.anim.slide_left_to_center, R.anim.slide_center_to_left, R.anim.slide_left_to_center, R.anim.slide_center_to_left
            )
        }

        fragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, fragment, url)
            .setCustomAnimations(anims[0], anims[1], anims[2], anims[3])
            .addToBackStack(fragment.javaClass.simpleName)
            .show(fragment)
            .commitAllowingStateLoss()
    }
}