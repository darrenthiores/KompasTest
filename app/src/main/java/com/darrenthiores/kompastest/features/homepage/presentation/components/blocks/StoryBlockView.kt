package com.darrenthiores.kompastest.features.homepage.presentation.components.blocks

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.darrenthiores.kompastest.app.theme.GrayLight
import com.darrenthiores.kompastest.app.theme.GrayRegular
import com.darrenthiores.kompastest.core.models.articles.Article
import com.darrenthiores.kompastest.core.models.stories.Story
import com.darrenthiores.kompastest.features.homepage.presentation.components.items.FullArticleItem
import com.darrenthiores.kompastest.features.homepage.presentation.components.items.TextArticleItem

@Composable
fun StoryBlockView(
    modifier: Modifier = Modifier,
    story: Story,
    onClick: (Article) -> Unit,
    onClickShare: (Article) -> Unit,
    onBookmark: (Article) -> Unit,
) {
    val mainArticle = remember(story) {
        story.articles?.getOrNull(0)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surface
            )
            .padding(
                vertical = 8.dp
            ),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        StoryHero(
            modifier = Modifier,
            story = story
        )

        FullArticleItem(
            modifier = Modifier,
            article = mainArticle,
            onClick = {
                mainArticle?.let { article ->
                    onClick(article)
                }
            },
            onClickShare = {
                mainArticle?.let { article ->
                    onClickShare(article)
                }
            },
            onBookmark = {
                mainArticle?.let { article ->
                    onBookmark(article)
                }
            }
        )

        if (story.articles?.drop(1)?.isNotEmpty() == true) {
            Column {
                story.articles.drop(1).forEach { article ->
                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 16.dp
                            ),
                        thickness = 1.dp,
                        color = GrayLight
                    )

                    TextArticleItem(
                        modifier = Modifier,
                        article = article,
                        onClick = {
                            onClick(article)
                        },
                        onClickShare = {
                            onClickShare(article)
                        },
                        onBookmark = {
                            onBookmark(article)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun StoryHero(
    modifier: Modifier = Modifier,
    story: Story
) {
    val mainArticle = remember(story) {
        story.articles?.getOrNull(0)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {

                }
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .weight(1f),
                text = story.section.orEmpty(),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.primary
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

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = GrayLight
                    ),
                model = mainArticle?.imageUrl,
                contentDescription = mainArticle?.imageDescription,
                contentScale = ContentScale.Crop
            )

            mainArticle?.mediaCount?.let { mediaCount ->
                if (mediaCount > 0) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .background(
                                color = Color.Black.copy(
                                    alpha = 0.5f
                                ),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(20.dp),
                            imageVector = Icons.Filled.PhotoCamera,
                            contentDescription = null,
                            tint = Color.White
                        )

                        Text(
                            text = "$mediaCount",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = Color.White
                            )
                        )
                    }
                }
            }
        }
    }
}