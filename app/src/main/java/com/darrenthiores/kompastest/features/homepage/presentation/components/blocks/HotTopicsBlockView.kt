package com.darrenthiores.kompastest.features.homepage.presentation.components.blocks

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.darrenthiores.kompastest.app.theme.GrayBackground
import com.darrenthiores.kompastest.app.theme.GrayLight
import com.darrenthiores.kompastest.app.theme.GrayRegular
import com.darrenthiores.kompastest.app.theme.YellowRegular
import com.darrenthiores.kompastest.core.models.highlights.HotTopics
import com.darrenthiores.kompastest.core.models.highlights.Topic

@Composable
fun HotTopicsBlockView(
    modifier: Modifier = Modifier,
    hotTopics: HotTopics
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surface
            )
            .padding(
                vertical = 24.dp,
                horizontal = 16.dp
            ),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            VerticalDivider(
                modifier = Modifier
                    .fillMaxHeight(),
                thickness = 4.dp,
                color = YellowRegular
            )

            Text(
                text = "Topik Hangat",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
        }

        if (hotTopics.topics.isNotEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                hotTopics.topics.forEach { topic ->
                    TopicItem(
                        modifier = Modifier,
                        topic = topic,
                        onClick = {

                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun TopicItem(
    modifier: Modifier = Modifier,
    topic: Topic,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable {
                onClick.invoke()
            }
            .border(
                width = 1.dp,
                color = GrayBackground,
                shape = RoundedCornerShape(12.dp)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier
                .size(50.dp)
                .background(
                    color = GrayLight
                ),
            model = topic.imageUrl,
            contentDescription = topic.imageDescription
        )

        Row(
            modifier = Modifier
                .weight(1f)
                .padding(
                    horizontal = 16.dp
                ),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .weight(1f),
                text = topic.title,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Icon(
                modifier = Modifier
                    .size(24.dp),
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = GrayRegular
            )
        }
    }
}