package com.darrenthiores.kompastest.core_ui.utils

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun DynamicStatusBarColor(
    isAppearanceLightStatusBars: Boolean
) {
    val view = LocalView.current

    LaunchedEffect (view, isAppearanceLightStatusBars) {
        val window = (view.context as? Activity)?.window
            ?: return@LaunchedEffect
        val controller = WindowCompat.getInsetsController(window, window.decorView)

        controller.isAppearanceLightStatusBars = isAppearanceLightStatusBars
    }
}