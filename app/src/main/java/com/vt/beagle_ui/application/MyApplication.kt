package com.vt.beagle_ui.application

import android.app.Application
import com.vt.beagle_ui.beagle_config.BeagleSetup

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        instance = this

        BeagleSetup().init(instance)
    }

    companion object {
        lateinit var instance: Application
    }
}