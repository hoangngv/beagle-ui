package com.vt.beagle_ui.beagle_components.custom_components

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vt.beagle_ui.R
import kotlinx.android.synthetic.main.layout_bottom_navigation_view.view.*
import java.util.*


class BottomNavigationView(context: Context) : LinearLayout(context) {

    init {
        View.inflate(context, R.layout.layout_bottom_navigation_view, this)
        invalidate()
        requestLayout()
    }

    fun setupMenu(menuItems: ArrayList<Array<String>>) {
        if (menuItems.size > 0) {
            val menu = navigationBar.menu
            var currentTab = ""

            Glide
                .with(this)
                .load(menuItems[0][0])
                .into(object : SimpleTarget<Drawable?>() {
                    override fun onResourceReady(
                        resource: Drawable,
                        @Nullable transition: Transition<in Drawable?>?
                    ) {
                        Log.d("dLog", menuItems[0][0])
                        menu.findItem(R.id.default_page).apply {
                            icon = resource
                            setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
                            title = menuItems[0][1]
                            currentTab = title.toString()
                        }
                    }
                })

            for (i in 1 until menuItems.size) {
                Glide
                    .with(this)
                    .load(menuItems[i][0])
                    .into(object : SimpleTarget<Drawable?>() {
                        override fun onResourceReady(
                            resource: Drawable,
                            @Nullable transition: Transition<in Drawable?>?
                        ) {
                            menu.add(Menu.NONE, i, Menu.NONE, menuItems[i][1]).apply {
                                icon = resource
                                setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
                            }
                        }
                    })
            }

            navigationBar.setOnNavigationItemSelectedListener { item ->
                Toast.makeText(context, item.title, Toast.LENGTH_SHORT).show()
                if (item.title != currentTab) {
                    for (menuItem in menuItems) {
                        if (menuItem[1] == item.title) {
                            currentTab = item.title.toString()

//                            val intent = context.newServerDrivenIntent<AppBeagleActivity>(ScreenRequest(menuItem[2]))
//                            startActivity(context, intent, null)
                        }
                    }
                }
                true
            }

            setNavigationTextColor(navigationBar)
        } else {
            Log.d("dLog", "MenuItems size is zero or null")
        }
    }

    private fun setNavigationTextColor(navigationView: BottomNavigationView) {
        val colors = intArrayOf(
            ContextCompat.getColor(context, R.color.colorGrayDark),
            ContextCompat.getColor(context, R.color.colorPrimaryBlue)
        )
        val states = arrayOf(
            intArrayOf(android.R.attr.state_enabled, -android.R.attr.state_checked),
            intArrayOf(android.R.attr.state_enabled, android.R.attr.state_checked)
        )

        navigationView.itemTextColor = ColorStateList(states, colors)
        navigationView.itemIconTintList = ColorStateList(states, colors)
    }
}