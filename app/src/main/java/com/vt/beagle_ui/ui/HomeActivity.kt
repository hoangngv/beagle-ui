package com.vt.beagle_ui.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.content.ContextCompat
import br.com.zup.beagle.android.utils.newServerDrivenIntent
import br.com.zup.beagle.android.view.ScreenRequest
import com.vt.beagle_ui.R
import com.vt.beagle_ui.extensions.changeStatusBarColor
import com.vt.beagle_ui.extensions.toast

class HomeActivity : AppCompatActivity() {

    private var backPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        renderServerDrivenUI()
    }

    private fun renderServerDrivenUI() {
        val intent = this.newServerDrivenIntent<AppBeagleActivity>(ScreenRequest("/uiController/personal"))
        startActivity(intent)
        finish()

//        server_driven_container.loadView(this, ScreenRequest("/uiController/personal"), object : OnServerStateChanged {
//            override fun invoke(serverState: ServerDrivenState) {
//                Log.d("dLog", serverState.toString())
//            }
//        })
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