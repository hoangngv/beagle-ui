package com.vt.beagle_ui.beagle_components.custom_widget_view

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vt.beagle_ui.R
import com.vt.beagle_ui.model.BadgeModel
import com.vt.beagle_ui.ui.home.HomeFragment
import com.vt.beagle_ui.utils.bus.SingleBus
import com.vt.beagle_ui.utils.bus.SingleBusKey
import kotlinx.android.synthetic.main.layout_bottom_navigation_view.view.*
import kotlinx.coroutines.Job
import java.util.*

class BottomNavigationView(context: Context) : LinearLayout(context) {

    private var notificationMenuItemId = 0
    private var currentTab = ""
    private var tempFragment = HomeFragment()

    init {
        View.inflate(context, R.layout.layout_bottom_navigation_view, this)
        initBus()
        invalidate()
        requestLayout()
    }

    private fun initBus(){
        val busJobs : MutableList<Job> = arrayListOf()
        busJobs.apply {
            add(SingleBus.receive(SingleBusKey.SET_BADGE_NUMBER) {
                val badgeModel = it as BadgeModel
                val notificationBadge = navigationBar.getOrCreateBadge(notificationMenuItemId)
                notificationBadge.number = badgeModel.number
                notificationBadge.backgroundColor = Color.parseColor(badgeModel.badgeBackgroundColor)
                notificationBadge.badgeTextColor = Color.parseColor(badgeModel.badgeTextColor)
            })
        }
    }

    fun setupMenu(
        menuItems: ArrayList<Array<String>>,
        selectedColor: String? = "#3596EC",
        unselectedColor: String? = "#788793",
        activity: AppCompatActivity
    ) {
        if (menuItems.size > 0) {
            val fragmentManager: FragmentManager = activity.supportFragmentManager
            setupMenuItems(menuItems)
            setNavigationTextColor(navigationBar, selectedColor, unselectedColor)
            generateFragment(menuItems, fragmentManager)
            setupListener(menuItems, fragmentManager)
        } else {
            Log.d("dLog", "MenuItems size is zero or null")
        }
    }

    private fun setupMenuItems(menuItems: ArrayList<Array<String>>) {
        val menu = navigationBar.menu

        for (i in 0 until menuItems.size) {
            Glide
                .with(this)
                .load(menuItems[i][0])
                .into(object : SimpleTarget<Drawable?>() {
                    override fun onResourceReady(
                        resource: Drawable,
                        @Nullable transition: Transition<in Drawable?>?
                    ) {
                        if (i == 0) {
                            menu.findItem(R.id.default_page).apply {
                                icon = resource
                                setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
                                title = menuItems[i][1]
                                currentTab = title.toString()
                            }
                        } else {
                            val menuItemId = ViewCompat.generateViewId()
                            menu.add(Menu.NONE, menuItemId, Menu.NONE, menuItems[i][1]).apply {
                                icon = resource
                                setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
                            }

                            if (i == menuItems.size-1) {
                                notificationMenuItemId = menuItemId
                            }
                        }
                    }
                })
        }
    }

    private fun generateFragment(
        menuItems: ArrayList<Array<String>>,
        fragmentManager: FragmentManager)
    {
        val fragmentTransaction = fragmentManager.beginTransaction()
        var homeFragmentInstance : HomeFragment

        for (i in 0 until menuItems.size) {
            homeFragmentInstance = HomeFragment.newInstance(menuItems[i][2]) // pass the destination
            fragmentTransaction.add(
                R.id.fragment_container,
                homeFragmentInstance,
                menuItems[i][1]
            ) // use title to diff fragment instances
            if (i == 0) {
                fragmentTransaction.show(homeFragmentInstance)
                tempFragment = homeFragmentInstance
            } else {
                fragmentTransaction.hide(homeFragmentInstance)
            }
        }
        fragmentTransaction.commit()
    }

    private fun setupListener(
        menuItems: ArrayList<Array<String>>,
        fragmentManager: FragmentManager)
    {
        // listener
        navigationBar.setOnNavigationItemSelectedListener { item ->
            if (item.title != currentTab) {
                for (menuItem in menuItems) {
                    if (menuItem[1] == item.title) {
                        val fragmentTrans = fragmentManager.beginTransaction()
                        currentTab = item.title.toString()
                        fragmentTrans.hide(tempFragment)
                        val shownFragment = fragmentManager.findFragmentByTag(currentTab) as HomeFragment
                        fragmentTrans.show(shownFragment)
                        tempFragment = shownFragment
                        fragmentTrans.commit()
                    }
                }
            }
            true
        }
    }

    private fun setNavigationTextColor(
        navigationView: BottomNavigationView,
        selectedColor: String? = "#3596EC",
        unselectedColor: String? = "#788793"
    ) {
        val colors = intArrayOf(
            Color.parseColor(unselectedColor),
            Color.parseColor(selectedColor)
        )
        val states = arrayOf(
            intArrayOf(android.R.attr.state_enabled, -android.R.attr.state_checked),
            intArrayOf(android.R.attr.state_enabled, android.R.attr.state_checked)
        )

        navigationView.itemTextColor = ColorStateList(states, colors)
        navigationView.itemIconTintList = ColorStateList(states, colors)
    }
}