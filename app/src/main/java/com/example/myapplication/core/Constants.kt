package com.example.myapplication.core

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut

object Constants {

    /**
     * Default logging tag
     */
    const val TAG = "Gallery"

    /**
     * Value in ms
     */
    const val DEFAULT_LOW_VELOCITY_SWIPE_DURATION = 150

    /**
     * Smooth enough at 300ms
     */
    const val DEFAULT_NAVIGATION_ANIMATION_DURATION = 300

      object Animation {

        val enterAnimation = fadeIn(tween(DEFAULT_LOW_VELOCITY_SWIPE_DURATION))
        val exitAnimation = fadeOut(tween(DEFAULT_LOW_VELOCITY_SWIPE_DURATION))

        val navigateInAnimation = fadeIn(tween(DEFAULT_NAVIGATION_ANIMATION_DURATION))
        val navigateUpAnimation = fadeOut(tween(DEFAULT_NAVIGATION_ANIMATION_DURATION))

        fun enterAnimation(durationMillis: Int): EnterTransition =
            fadeIn(tween(durationMillis))

        fun exitAnimation(durationMillis: Int): ExitTransition =
            fadeOut(tween(durationMillis))

    }

    /**
     * Date format used in media groups
     */
    const val DEFAULT_DATE_FORMAT = "hh:mm:ss"
}