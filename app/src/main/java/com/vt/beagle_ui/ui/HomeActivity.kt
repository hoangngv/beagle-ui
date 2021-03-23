package com.vt.beagle_ui.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import br.com.zup.beagle.android.components.Text
import br.com.zup.beagle.android.components.page.PageIndicator
import br.com.zup.beagle.android.components.page.PageView
import br.com.zup.beagle.android.context.ContextData
import br.com.zup.beagle.android.context.expressionOf
import br.com.zup.beagle.android.context.valueOf
import br.com.zup.beagle.android.utils.loadView
import br.com.zup.beagle.android.utils.toView
import br.com.zup.beagle.android.view.ScreenRequest
import br.com.zup.beagle.android.view.ServerDrivenState
import br.com.zup.beagle.android.view.custom.OnServerStateChanged
import br.com.zup.beagle.ext.applyFlex
import br.com.zup.beagle.widget.core.AlignSelf
import br.com.zup.beagle.widget.core.Flex
import br.com.zup.beagle.widget.core.TextAlignment
import com.vt.beagle_ui.R
import com.vt.beagle_ui.extensions.changeStatusBarColor
import com.vt.beagle_ui.extensions.toast
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private var backPressedOnce = false
    private var fragmentTemp: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        renderServerDrivenUI()
    }

    private fun renderDeclarativeUI() {
        server_driven_container.addView(testPageView().toView(this))
    }

    private fun renderServerDrivenUI() {
        banner.loadView(this, ScreenRequest("/uiController/banner"), object : OnServerStateChanged {
            override fun invoke(serverState: ServerDrivenState) {
                Log.d("dLog", serverState.toString())
            }
        })

        bottomNavigationView.loadView(
            this,
            ScreenRequest("/uiController/bottomNavigationView"),
            object : OnServerStateChanged {
                override fun invoke(serverState: ServerDrivenState) {
                    Log.d("dLog", serverState.toString())
                }
            })

//        val intent = this.newServerDrivenIntent<AppBeagleActivity>(ScreenRequest("/uiController/home"))
//        startActivity(intent)
//        finish()
    }

    override fun onBackPressed() {
        changeStatusBarColor(ContextCompat.getColor(this, R.color.colorGreen))

        if (backPressedOnce) {
            super.onBackPressed()
            return
        }

        this.backPressedOnce = true
        applicationContext.toast(getString(R.string.press_again_to_exit))

        Handler(Looper.getMainLooper()).postDelayed({
            backPressedOnce = false
        }, 2000)
    }

    private fun testPageView() = PageView(
        context = ContextData(
            id = "pages",
            value = listOf(
                "Page 1",
                "Page 2",
                "Page 3"
            )
        ),
        pageIndicator = PageIndicator(
            selectedColor = "#000000",
            unselectedColor = "#888888"
        ),
        children = listOf(
            Text(
                text = expressionOf("@{pages[0]}"),
                alignment = valueOf(TextAlignment.CENTER)
            ).applyFlex(
                Flex(
                    alignSelf = AlignSelf.CENTER,
                    grow = 1.0
                )
            ),
            Text(
                text = expressionOf("@{pages[1]}"),
                alignment = valueOf(TextAlignment.CENTER)
            ).applyFlex(
                Flex(
                    alignSelf = AlignSelf.CENTER,
                    grow = 1.0
                )
            ),
            Text(
                text = expressionOf("@{pages[2]}"),
                alignment = valueOf(TextAlignment.CENTER)
            ).applyFlex(
                Flex(
                    alignSelf = AlignSelf.CENTER,
                    grow = 1.0
                )
            )
        )
    )

    private fun initFragment() {
//        fragmentManager = supportFragmentManager
//        fragmentTransaction = fragmentManager.beginTransaction()
//        homeTaskFragment = HomeTaskFragment()
//        homeRequestFragment = HomeRequestFragment()
//        homePageNewFragment = HomePageNewFragment()
////        homeNotificationFragment = new HomeNotificationFragment();
//        //        homeNotificationFragment = new HomeNotificationFragment();
//        homeTeamManageFragment = HomeTeamManageFragment()
////        homeChatFragment = new HomeChatFragment();
//
//        //        homeChatFragment = new HomeChatFragment();
//        fragmentTransaction.add(R.id.frame_home, homeTaskFragment)
////        fragmentTransaction.add(R.id.frame_home, homeTaskNewFragment);
//        //        fragmentTransaction.add(R.id.frame_home, homeTaskNewFragment);
//        fragmentTransaction.add(R.id.frame_home, homeRequestFragment)
//        fragmentTransaction.add(R.id.frame_home, homePageNewFragment)
//        fragmentTransaction.add(R.id.frame_home, homeTeamManageFragment)
////        fragmentTransaction.add(R.id.frame_home, homeNotificationFragment);
////        fragmentTransaction.add(R.id.frame_home, homeChatFragment);
//
//        //        fragmentTransaction.add(R.id.frame_home, homeNotificationFragment);
////        fragmentTransaction.add(R.id.frame_home, homeChatFragment);
//        fragmentTransaction.show(homePageNewFragment)
//        fragmentTransaction.hide(homeTaskFragment)
//        //fragmentTransaction.hide(homeTaskNewFragment);
//        //fragmentTransaction.hide(homeTaskNewFragment);
//        fragmentTransaction.hide(homeRequestFragment)
////        fragmentTransaction.hide(homeNotificationFragment);
//        //        fragmentTransaction.hide(homeNotificationFragment);
//        fragmentTransaction.hide(homeTeamManageFragment)
////        fragmentTransaction.hide(homeChatFragment);
//        //        fragmentTransaction.hide(homeChatFragment);
//        fragmentTemp = homePageNewFragment
//
//        fragmentTransaction.commit()
    }

    private fun showFragment(hide: Fragment?, show: Fragment) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.hide(hide!!)
        transaction.show(show)
        fragmentTemp = show
        transaction.commit()
    }
}