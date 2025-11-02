package com.darrenthiores.kompastest.core_ui.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.topElevation(
    elevation: Dp = 8.dp
): Modifier = this.then(
    Modifier.drawWithContent {
        val paddingPx = elevation.toPx()

        clipRect(
            left = 0f,
            top = -paddingPx
        ) {
            this@drawWithContent.drawContent()
        }
    }
)