package com.vt.beagle_ui.beagle_config

import br.com.zup.beagle.android.annotation.BeagleComponent
import br.com.zup.beagle.android.setup.DesignSystem
import com.vt.beagle_ui.R

@BeagleComponent
class AppDesignSystem: DesignSystem() {
    override fun textStyle(id: String): Int? {
        return when(id) {
            "TextStyle" -> R.style.TextStyle
            "TextAccessProfile" -> R.style.TextAccessProfile
            "TextTitleProfile" -> R.style.TextTitleProfile
            "h1" -> R.style.TextTemplateCard
            else -> R.style.TextDefault
        }
    }

    override fun buttonStyle(id: String): Int? {
        return when(id) {
            "button" -> R.style.DesignSystem_Button_Default
            "ButtonRightArrow" -> R.style.DesignSystem_Button_RightArrow
            else -> R.style.DesignSystem_Button_Default
        }
    }

    override fun toolbarStyle(id: String): Int? {
        return when(id){
            "toolbar" -> R.style.DesignSystem_Toolbar_Center
            else -> R.style.DesignSystem_Toolbar_Center
        }
    }

    override fun inputTextStyle(id: String): Int? {
        return when(id) {
            "TextInput" -> R.style.TextInput
            else -> R.style.TextInput
        }
    }

    override fun image(id: String): Int? {
        return when(id) {
            "mobile" -> R.drawable.ic_launcher_foreground
            "right-arrow" -> R.drawable.ic_right_arrow
            else -> android.R.drawable.ic_menu_help
        }
    }
}