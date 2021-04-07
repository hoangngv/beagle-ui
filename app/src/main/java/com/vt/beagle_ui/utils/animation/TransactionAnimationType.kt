package com.vt.beagle_ui.utils.animation

import com.vt.beagle_ui.R

enum class TransactionAnimationType {
    TYPE_DEFAULT,
    TYPE_SLIDE_BOTTOM,
    TYPE_SLIDE_TOP,
    TYPE_SLIDE_SUB_LEFT_RIGHT,
    TYPE_FADE,
    TYPE_NO_ANIMATION,
    TYPE_SLIDE_INV_DEFAULT;

    open fun getCustomTransaction(): IntArray {
        return when (this) {
            TYPE_SLIDE_BOTTOM -> intArrayOf(
                R.anim.slide_bot_to_center,
                R.anim.no_animation,
                R.anim.no_animation,
                R.anim.slide_center_to_bot
            )
            TYPE_SLIDE_TOP -> intArrayOf(
                R.anim.slide_top_to_center,
                R.anim.no_animation,
                R.anim.no_animation,
                R.anim.slide_center_to_top
            )
            TYPE_SLIDE_SUB_LEFT_RIGHT -> intArrayOf(
                R.anim.slide_right_to_center,
                R.anim.slide_sub_center_to_left,
                R.anim.slide_sub_left_to_center,
                R.anim.slide_center_to_right
            )
            TYPE_NO_ANIMATION -> intArrayOf(0, 0, 0, 0)
            TYPE_FADE -> intArrayOf(
                R.anim.fade_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.fade_out
            )
            TYPE_SLIDE_INV_DEFAULT -> intArrayOf(
                R.anim.slide_left_to_center,
                R.anim.slide_center_to_right,
                R.anim.slide_right_to_center,
                R.anim.slide_center_to_left
            )
            else -> intArrayOf(
                R.anim.slide_right_to_center,
                R.anim.slide_center_to_left,
                R.anim.slide_left_to_center,
                R.anim.slide_center_to_right
            )
        }
    }
}