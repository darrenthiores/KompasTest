package com.darrenthiores.kompastest.core_ui.empty

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.darrenthiores.kompastest.core.utils.resources.Constants.UI_EMPTY_MESSAGE

@Composable
fun EmptyListView(
    modifier: Modifier = Modifier,
    message: String = UI_EMPTY_MESSAGE
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}