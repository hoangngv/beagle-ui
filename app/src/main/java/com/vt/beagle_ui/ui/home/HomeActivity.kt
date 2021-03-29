package com.vt.beagle_ui.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
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
    private var fragmentManager: FragmentManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        renderServerDrivenUI()
    }

    private fun renderDeclarativeUI() {
        fragment_container.addView(testPageView().toView(this))
    }

    private fun renderServerDrivenUI() {
        bottomNavigationView.loadView(
            this,
            ScreenRequest("/uiController/bottomNavigationView"),
            object : OnServerStateChanged {
                override fun invoke(serverState: ServerDrivenState) {
                    Log.d("dLog", serverState.toString())
                    when (serverState) {
                        is ServerDrivenState.Started -> {
                            layout_loading.visibility = View.VISIBLE
                        }
                        is ServerDrivenState.Finished -> {
                            layout_loading.visibility = View.GONE
                        }
                        is ServerDrivenState.Error -> {
                            serverState.throwable.printStackTrace()
                        }
                        ServerDrivenState.Canceled -> {
                        }
                        is ServerDrivenState.Loading -> {

                        }
                        ServerDrivenState.Success -> {

                        }
                    }
                }
            })
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
}