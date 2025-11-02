package com.darrenthiores.kompastest.features.homepage.presentation.components.blocks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.darrenthiores.kompastest.app.theme.GrayLight
import com.darrenthiores.kompastest.core.models.articles.Article
import com.darrenthiores.kompastest.features.homepage.presentation.components.items.FullArticleItem
import com.darrenthiores.kompastest.features.homepage.presentation.components.items.ImageArticleItem

@Composable
fun ArticlesBlockView(
    modifier: Modifier = Modifier,
    articles: List<Article>,
    onClick: (Article) -> Unit,
    onClickShare: (Article) -> Unit,
    onBookmark: (Article) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        articles.forEachIndexed { index, article ->
            if (index == 0) {
                Column {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxSize()
                            .height(240.dp)
                            .background(
                                color = GrayLight
                            ),
                        model = article.imageUrl,
                        contentDescription = article.imageDescription,
                        contentScale = ContentScale.Crop
                    )

                    FullArticleItem(
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.surface
                            )
                            .padding(
                                vertical = 24.dp
                            ),
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
            else {
                ImageArticleItem(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.surface
                        ),
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