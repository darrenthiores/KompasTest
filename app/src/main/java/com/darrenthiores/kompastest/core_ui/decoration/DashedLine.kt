package com.darrenthiores.kompastest.core_ui.decoration

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.Dp
import com.darrenthiores.kompastest.app.theme.GrayRegular

@Composable
fun DashedLine(
    modifier: Modifier = Modifier,
    color: Color = GrayRegular,
    strokeWidth: Dp = Dp(1.5f),
    dashLength: Float = 10f,
    gapLength: Float = 10f
) {
    Canvas(modifier = modifier) {
        val strokeWidthPx = strokeWidth.toPx()
        drawLine(
            color = color,
            start = Offset(x = size.width / 2, y = 0f),
            end = Offset(x = size.width / 2, y = size.height),
            strokeWidth = strokeWidthPx,
            cap = StrokeCap.Round,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(dashLength, gapLength), 0f)
        )
    }
}
