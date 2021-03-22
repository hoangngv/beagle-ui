package com.vt.beagle_ui.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.core.content.ContextCompat
import br.com.zup.beagle.android.components.Text
import br.com.zup.beagle.android.components.page.PageIndicator
import br.com.zup.beagle.android.components.page.PageView
import br.com.zup.beagle.android.context.ContextData
import br.com.zup.beagle.android.context.expressionOf
import br.com.zup.beagle.android.context.valueOf
import br.com.zup.beagle.android.utils.loadView
import br.com.zup.beagle.android.utils.newServerDrivenIntent
import br.com.zup.beagle.android.utils.renderScreen
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
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity() {

    private var backPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        renderServerDrivenUI()
    }

    private fun renderDeclarativeUI() {
        server_driven_container.addView(testPageView().toView(this))
    }

    private fun renderServerDrivenUI() {
        val intent = this.newServerDrivenIntent<AppBeagleActivity>(ScreenRequest("/uiController/home"))
        startActivity(intent)
        finish()
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
            Text(text = expressionOf("@{pages[0]}"), alignment = valueOf(TextAlignment.CENTER)).applyFlex(
                Flex(
                    alignSelf = AlignSelf.CENTER,
                    grow = 1.0
                )
            ),
            Text(text = expressionOf("@{pages[1]}"), alignment = valueOf(TextAlignment.CENTER)).applyFlex(
                Flex(
                    alignSelf = AlignSelf.CENTER,
                    grow = 1.0
                )
            ),
            Text(text = expressionOf("@{pages[2]}"), alignment = valueOf(TextAlignment.CENTER)).applyFlex(
                Flex(
                    alignSelf = AlignSelf.CENTER,
                    grow = 1.0
                )
            )
        )
    )
}