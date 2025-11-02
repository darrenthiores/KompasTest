package com.darrenthiores.kompastest.core_ui.bottom_sheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBottomSheet(
    onDismiss: () -> Unit = {},
    isCancellable: Boolean = true,
    skipPartiallyExpanded: Boolean = true,
    content: @Composable (sheetState : SheetState) -> Unit
) {
    val confirmStateChange: (SheetValue) -> Boolean = remember{
        {isCancellable}
    }

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded,
        confirmStateChange
    )

    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        containerColor = MaterialTheme.colorScheme.background,
        onDismissRequest = {
            scope.launch {
                sheetState.show()
            }
            onDismiss()
        },
        shape = RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp
        ),
        sheetState = sheetState,
        dragHandle = { DragHandle() },
        tonalElevation = 1.dp
    ) {
        content(sheetState)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBottomSheet(
    modifier: Modifier = Modifier,
    title: String,
    onDismiss: () -> Unit = {},
    withIcon: Boolean = false,
    isCancellable: Boolean = true,
    skipPartiallyExpanded: Boolean = true,
    content: @Composable (sheetState : SheetState) -> Unit
) {
    val localDensity = LocalDensity.current
    val topSafeArea = with(localDensity) { WindowInsets.safeDrawing.getTop(this).toDp() }

    AppBottomSheet(
        onDismiss = onDismiss,
        isCancellable = isCancellable,
        skipPartiallyExpanded = skipPartiallyExpanded
    ) { sheetState ->
        BoxWithConstraints {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .height(maxHeight - topSafeArea)
                    .padding(
                        horizontal = 16.dp
                    ),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (withIcon) {
                        IconButton(
                            onClick = onDismiss
                        ) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Close Bottom Sheet"
                            )
                        }
                    }

                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleLarge
                    )
                }

                content(sheetState)
            }
        }
    }
}

@Composable
private fun DragHandle(modifier: Modifier = Modifier){
    Surface(
        modifier =
            modifier.padding(vertical = 16.dp),
        color = Color.LightGray,
        shape = MaterialTheme.shapes.large
    ) {
        Box(Modifier.size(width = 56.dp, height = 4.dp))
    }
}