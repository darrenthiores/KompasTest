package com.darrenthiores.kompastest.features.articles.presentation.article_detail.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.darrenthiores.kompastest.app.theme.GrayBackground
import com.darrenthiores.kompastest.app.theme.GrayLight
import com.darrenthiores.kompastest.app.theme.GrayRegular
import com.darrenthiores.kompastest.core.models.articles.Article
import com.darrenthiores.kompastest.features.articles.domain.models.ArticleDetail

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun ArticleDetailReads(
    modifier: Modifier = Modifier,
    article: ArticleDetail,
    onClick: (Article) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surface
            )
            .padding(
                vertical = 24.dp
            ),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(
                    horizontal = 16.dp
                ),
            text = "Baca Lainnya",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Medium
            )
        )

        if (article.relatedArticles.isNotEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                article.relatedArticles.forEach { relatedArticle ->
                    ArticleRow(
                        modifier = Modifier,
                        article = relatedArticle,
                        onClick = {
                            onClick(relatedArticle)
                        }
                    )

                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 16.dp
                            ),
                        thickness = 1.dp,
                        color = GrayBackground
                    )
                }
            }
        }
    }
}

@Composable
private fun ArticleRow(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: () -> Unit
) {
    val informationText = remember(article) {
        var text = ""

        article.publishedTime?.let { publishedTime ->
            text += publishedTime
        }

        if (article.publishedTime != null && article.label != null) {
            text += " • "
        }

        article.label?.let { label ->
            text += label
        }

        text
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClick.invoke()
            }
            .padding(16.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = informationText,
                style = MaterialTheme.typography.labelMedium.copy(
                    color = GrayRegular
                ),
                maxLines = 1
            )
        }

        AsyncImage(
            modifier = Modifier
                .size(100.dp)
                .background(
                    color = GrayLight
                ),
            model = article.imageUrl,
            contentDescription = article.imageDescription,
            contentScale = ContentScale.Crop
        )
    }
}