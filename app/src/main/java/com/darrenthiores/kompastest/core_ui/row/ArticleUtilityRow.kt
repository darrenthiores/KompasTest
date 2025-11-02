package com.darrenthiores.kompastest.core_ui.row

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Headset
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Headset
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ArticleUtilityRow(
    modifier: Modifier = Modifier,
    onClickShare: () -> Unit,
    isBookmarked: Boolean,
    onBookmark: () -> Unit,
    isAudioActive: Boolean,
    isAudioBuffering: Boolean = false,
    onClickAudio: () -> Unit,
    color: Color = MaterialTheme.colorScheme.onSurface
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = onClickShare
        ) {
            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = "Share Article",
                tint = color
            )
        }

        IconButton(
            onClick = onBookmark
        ) {
            Icon(
                imageVector = if (isBookmarked) Icons.Default.Bookmark
                else Icons.Default.BookmarkBorder,
                contentDescription = if (isBookmarked) "UnBookmark Article"
                else "Bookmark Article",
                tint = color
            )
        }

        IconButton(
            onClick = onClickAudio
        ) {
            if (isAudioBuffering) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(20.dp),
                    color = color,
                    trackColor = color.copy(
                        alpha = 0.5f
                    )
                )
            }
            else {
                Icon(
                    imageVector = if (isAudioActive) Icons.Default.Headset
                    else Icons.Outlined.Headset,
                    contentDescription = if (isAudioActive) "Stop Listening"
                    else "Listen to Article",
                    tint = color
                )
            }
        }
    }
}