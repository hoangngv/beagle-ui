package com.vt.beagle_ui.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.Toolbar
import br.com.zup.beagle.android.annotation.RegisterController
import br.com.zup.beagle.android.view.BeagleActivity
import br.com.zup.beagle.android.view.ServerDrivenState
import com.vt.beagle_ui.R
import kotlinx.android.synthetic.main.activity_beagle.*

@RegisterController
class AppBeagleActivity : BeagleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beagle)

        Log.d("dLog", "setContentView")
    }

    override fun getServerDrivenContainerId(): Int = R.id.server_driven_container

    override fun getToolbar(): Toolbar = findViewById<Toolbar>(R.id.toolbar)

    override fun onServerDrivenContainerStateChanged(state: ServerDrivenState) {
        if (state is ServerDrivenState.Error) {
            Log.d("dLog", "Error")
        } else if (state is ServerDrivenState.Loading) {
            progress_bar.visibility = if (state.loading) View.VISIBLE else View.GONE
        }
    }
}