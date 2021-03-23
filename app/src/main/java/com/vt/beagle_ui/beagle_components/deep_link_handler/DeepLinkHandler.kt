package com.vt.beagle_ui.beagle_components.deep_link_handler

import android.content.Intent
import br.com.zup.beagle.android.annotation.BeagleComponent
import br.com.zup.beagle.android.navigation.DeepLinkHandler
import br.com.zup.beagle.android.widget.RootView
import com.vt.beagle_ui.ui.HomeActivity

@BeagleComponent
class AppDeepLinkHandler : DeepLinkHandler {
    override fun getDeepLinkIntent(
        rootView: RootView,
        path: String,
        data: Map<String, String>?,
        shouldResetApplication: Boolean,
    ): Intent {

        val safePath = SafePathMapped.values().find { safePathMapped ->
            safePathMapped.name == path
        }
        // check deep link it is mapped in your application
        if (safePath != null) {
            return Intent(path)
        }

        //OR GO TO HOME ACTIVITY
        return Intent(rootView.getContext(), HomeActivity::class.java)
    }
}


enum class SafePathMapped {
    DEEPLINK_ONE,
    DEEEPLINK_TWO
}