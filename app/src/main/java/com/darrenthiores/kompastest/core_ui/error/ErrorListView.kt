package com.darrenthiores.kompastest.core_ui.error

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.darrenthiores.kompastest.core.utils.resources.Constants.TRY_AGAIN_TEXT
import com.darrenthiores.kompastest.core.utils.resources.Constants.UI_ERROR_MESSAGE
import com.darrenthiores.kompastest.core_ui.button.AppButton

@Composable
fun ErrorListView(
    modifier: Modifier = Modifier,
    message: String = UI_ERROR_MESSAGE,
    buttonMessage: String = TRY_AGAIN_TEXT,
    onTryAgain: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp
            )
            .padding(
                bottom = 16.dp
            ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge
        )

        AppButton(
            text = buttonMessage,
            onClick = onTryAgain,
            contentPadding = PaddingValues(
                horizontal = 32.dp,
                vertical = 4.dp
            )
        )
    }
}