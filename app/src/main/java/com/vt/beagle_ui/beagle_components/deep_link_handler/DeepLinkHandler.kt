package com.vt.beagle_ui.beagle_components.deep_link_handler

import android.content.Intent
import br.com.zup.beagle.android.annotation.BeagleComponent
import br.com.zup.beagle.android.navigation.DeepLinkHandler
import br.com.zup.beagle.android.widget.RootView

@BeagleComponent
class AppDeepLinkHandler : DeepLinkHandler {
    override fun getDeepLinkIntent(
        rootView: RootView,
        path: String,
        data: Map<String, String>?,
        shouldResetApplication: Boolean
    ) = Intent(path)
}