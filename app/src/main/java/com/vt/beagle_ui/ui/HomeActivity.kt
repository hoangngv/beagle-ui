package com.vt.beagle_ui.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.core.content.ContextCompat
import br.com.zup.beagle.android.utils.loadView
import br.com.zup.beagle.android.utils.newServerDrivenIntent
import br.com.zup.beagle.android.utils.renderScreen
import br.com.zup.beagle.android.view.ScreenRequest
import br.com.zup.beagle.android.view.ServerDrivenState
import br.com.zup.beagle.android.view.custom.OnServerStateChanged
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

    private fun renderServerDrivenUI() {
//        val intent = this.newServerDrivenIntent<AppBeagleActivity>(ScreenRequest("/uiController/home"))
//        startActivity(intent)
//        finish()

        server_driven_container.loadView(this, ScreenRequest("/uiController/home"), object : OnServerStateChanged {
            override fun invoke(serverState: ServerDrivenState) {
                Log.d("dLog", serverState.toString())
            }
        })

//        server_driven_container.renderScreen(
//            activity = this,
//            screenJson = "{\n" +
//                    "  \"_beagleComponent_\": \"beagle:screenComponent\",\n" +
//                    "  \"identifier\": null,\n" +
//                    "  \"safeArea\": null,\n" +
//                    "  \"navigationBar\": null,\n" +
//                    "  \"child\": {\n" +
//                    "    \"_beagleComponent_\": \"beagle:button\",\n" +
//                    "    \"text\": \"Click me!\",\n" +
//                    "    \"styleId\": null,\n" +
//                    "    \"onPress\": [\n" +
//                    "      {\n" +
//                    "        \"_beagleAction_\": \"beagle:openNativeRoute\",\n" +
//                    "        \"route\": \"notification\",\n" +
//                    "        \"shouldResetApplication\": false\n" +
//                    "      }\n" +
//                    "    ],\n" +
//                    "    \"clickAnalyticsEvent\": null,\n" +
//                    "    \"enabled\": null,\n" +
//                    "    \"id\": null,\n" +
//                    "    \"style\": null,\n" +
//                    "    \"accessibility\": null\n" +
//                    "  },\n" +
//                    "  \"style\": null,\n" +
//                    "  \"screenAnalyticsEvent\": null,\n" +
//                    "  \"context\": null\n" +
//                    "}"
//        )
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
}