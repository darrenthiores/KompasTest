package com.darrenthiores.kompastest.features.homepage.presentation.components.blocks

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.darrenthiores.kompastest.R
import com.darrenthiores.kompastest.app.theme.GrayBackground
import com.darrenthiores.kompastest.app.theme.GrayDark
import com.darrenthiores.kompastest.app.theme.GrayLight
import com.darrenthiores.kompastest.app.theme.GrayRegular
import com.darrenthiores.kompastest.core.models.highlights.BreakingNews
import com.darrenthiores.kompastest.core_ui.row.ArticleUtilityRow
import com.darrenthiores.kompastest.features.homepage.presentation.components.items.TextArticleItem

@Composable
fun BreakingNewsBlockView(
    modifier: Modifier = Modifier,
    breakingNews: BreakingNews
) {
    val mainArticle = remember(breakingNews) {
        breakingNews.articles.getOrNull(0)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surface
            )
            .padding(
                top = 32.dp,
                bottom = 8.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        BreakingNewsHero(
            modifier = Modifier,
            breakingNews = breakingNews
        )

        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .background(color = GrayLight),
            model = mainArticle?.imageUrl,
            contentDescription = mainArticle?.imageDescription,
            contentScale = ContentScale.Crop
        )

        if (breakingNews.articles.drop(1).isNotEmpty()) {
            Column {
                breakingNews.articles.drop(1).forEach { article ->
                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 16.dp
                            ),
                        thickness = 1.dp,
                        color = GrayBackground
                    )

                    TextArticleItem(
                        modifier = Modifier,
                        article = article,
                        onClick = {

                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun BreakingNewsHero(
    modifier: Modifier = Modifier,
    breakingNews: BreakingNews
) {
    val article = remember(breakingNews) {
        breakingNews.articles.getOrNull(0)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            modifier = Modifier
                .height(32.dp)
                .fillMaxWidth(),
            painter = painterResource(R.drawable.breaking_news_icon),
            contentDescription = null
        )

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = article?.title ?: "-",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )
            )

            article?.description?.let { description ->
                Text(
                    text = description,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        color = GrayRegular
                    )
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                modifier = Modifier
                    .weight(1f),
                text = article?.publishedTime.orEmpty(),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = if (isSystemInDarkTheme()) GrayDark
                    else GrayRegular
                )
            )

            ArticleUtilityRow(
                modifier = modifier,
                onClickShare = { },
                isBookmarked = article?.bookmarked ?: false,
                onBookmark = { },
                isAudioActive = false,
                onClickAudio = { },
            )
        }
    }
}