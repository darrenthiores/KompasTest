package com.darrenthiores.kompastest.core_ui.bar

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.darrenthiores.kompastest.core.utils.resources.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    modifier: Modifier = Modifier,
    title: String = Constants.EMPTY_STRING,
    titleItem: @Composable (() -> Unit)? = null,
    leftActionItem: @Composable () -> Unit = { },
    rightActionItem: @Composable () -> Unit = { },
    windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
    colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    val textStyle = MaterialTheme.typography.titleLarge
    var resizedTextField by remember {
        mutableStateOf(textStyle)
    }
    var shouldDraw by remember {
        mutableStateOf(false)
    }

    TopAppBar(
        modifier = modifier,
        title = {
            titleItem?.let { item ->
                item()
            } ?: run {
                Text(
                    modifier = Modifier
                        .drawWithContent {
                            if (shouldDraw) {
                                drawContent()
                            }
                        },
                    text = title,
                    style = resizedTextField,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    softWrap = false,
                    onTextLayout = {
                        if (it.didOverflowWidth) {
                            resizedTextField = resizedTextField.copy(
                                fontSize = resizedTextField.fontSize * 0.95
                            )
                        }
                        else {
                            shouldDraw = true
                        }
                    }
                )
            }
        },
        navigationIcon = {
            leftActionItem()
        },
        actions = {
            rightActionItem()
        },
        windowInsets = windowInsets,
        colors = colors,
        scrollBehavior = scrollBehavior
    )
}