package com.vt.beagle_ui.ui

import android.content.res.Configuration
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import br.com.zup.beagle.android.action.Alert
import br.com.zup.beagle.android.components.Button
import br.com.zup.beagle.android.components.Text
import br.com.zup.beagle.android.components.layout.Container
import br.com.zup.beagle.android.components.layout.Screen
import br.com.zup.beagle.android.utils.loadView
import br.com.zup.beagle.android.utils.newServerDrivenIntent
import br.com.zup.beagle.android.utils.renderScreen
import br.com.zup.beagle.android.utils.toView
import br.com.zup.beagle.android.view.BeagleActivity
import br.com.zup.beagle.android.view.ScreenRequest
import br.com.zup.beagle.android.view.ServerDrivenState
import br.com.zup.beagle.android.view.custom.OnServerStateChanged
import br.com.zup.beagle.core.Style
import br.com.zup.beagle.ext.applyFlex
import br.com.zup.beagle.ext.applyStyle
import br.com.zup.beagle.ext.unitReal
import br.com.zup.beagle.widget.core.AlignSelf
import br.com.zup.beagle.widget.core.EdgeValue
import br.com.zup.beagle.widget.core.Flex
import br.com.zup.beagle.widget.core.FlexDirection
import com.vt.beagle_ui.R
import com.vt.beagle_ui.databinding.ActivityMainBinding
import com.vt.beagle_ui.extensions.changeStatusBarColor
import com.vt.beagle_ui.extensions.toast
import java.util.*

class MainActivity : AppCompatActivity() {

    private var backPressedOnce = false
    private val binding: ActivityMainBinding by lazy {ActivityMainBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        changeStatusBarColor(ContextCompat.getColor(this, R.color.colorGreen))
        setupLocale()

        //renderDeclarativeUI()
        renderServerDrivenUI()
    }

    private fun renderDeclarativeUI() {
        binding.frameLayoutServerDriven.addView(testScreen().toView(this))
    }

    private fun renderServerDrivenUI() {
//        val intent = this.newServerDrivenIntent<AppBeagleActivity>(ScreenRequest("/uiController/personal"))
//        startActivity(intent)

//        test_content.renderScreen(
//            activity = this,
//            screenJson = "JSON here"
//        )

        binding.frameLayoutServerDriven.loadView(this, ScreenRequest("/uiController/personal"), object : OnServerStateChanged {
            override fun invoke(serverState: ServerDrivenState) {
                Log.d("dLog", serverState.toString())
            }
        })
    }

    private fun setupLocale() {
        val locale = Locale("vi")
        Locale.setDefault(locale)
        val config = Configuration()
        if (Build.VERSION.SDK_INT >= 24) {
            config.setLocale(locale)
            baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
        } else {
            config.locale = locale
            baseContext.applicationContext.createConfigurationContext(config)
        }
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


    private fun testScreen() = Screen(
        child = Container(
            children = listOf(
                Text(
                    text = "Hello hoangnv65!",
                    styleId = "TextTitleProfile"
                ).applyStyle(
                    Style(margin = EdgeValue(top = 16.unitReal()),
                        flex = Flex(alignSelf = AlignSelf.CENTER)
                    )
                ),
                Text(
                    text = "Beagle is a cross-platform framework which provides usage of the " +
                            "Server-Driven UI concept, natively in iOS, Android and Web applications. " +
                            "By using Beagle, your team could easily change application's layout and" +
                            " data by just changing backend code."
                ).applyStyle(
                    Style(margin = EdgeValue(
                        left = 16.unitReal(),
                        right = 16.unitReal(),
                        top = 20.unitReal()
                        )
                    )
                ),
                Button(
                    "DRAW MONEY",
                    styleId = "button",
                    onPress = listOf(
                        Alert(
                            "Server-driven Button",
                            "I'm a server-based button",
                            labelOk = "Dismiss"
                        )
                    )
                ).applyStyle(
                    Style(margin = EdgeValue(
                            top = 20.unitReal(),
                            left = 16.unitReal(),
                            right = 16.unitReal()
                        )
                    )
                )
            )
        ).applyFlex(
            Flex(
                flexDirection = FlexDirection.COLUMN
            )
        )
    )
}