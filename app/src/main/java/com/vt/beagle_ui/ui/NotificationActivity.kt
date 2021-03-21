package com.vt.beagle_ui.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.com.zup.beagle.android.utils.loadView
import br.com.zup.beagle.android.utils.newServerDrivenIntent
import br.com.zup.beagle.android.view.ScreenRequest
import br.com.zup.beagle.android.view.ServerDrivenState
import br.com.zup.beagle.android.view.custom.OnServerStateChanged
import com.vt.beagle_ui.R
import kotlinx.android.synthetic.main.activity_notification.*

class NotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        renderServerDrivenUI()
    }

    private fun renderServerDrivenUI() {
//        val intent = this.newServerDrivenIntent<AppBeagleActivity>(ScreenRequest("/uiController/personal"))
//        startActivity(intent)
//        finish()
        frame_layout_server_driven.loadView(this, ScreenRequest("/uiController/personal"), object : OnServerStateChanged {
            override fun invoke(serverState: ServerDrivenState) {
                Log.d("dLog", serverState.toString())
            }
        })
    }
}