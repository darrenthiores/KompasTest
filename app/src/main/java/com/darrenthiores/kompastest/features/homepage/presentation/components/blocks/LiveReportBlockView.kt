package com.darrenthiores.kompastest.features.homepage.presentation.components.blocks

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.darrenthiores.kompastest.app.theme.GrayLight
import com.darrenthiores.kompastest.app.theme.GrayRegular
import com.darrenthiores.kompastest.app.theme.RedRegular
import com.darrenthiores.kompastest.core.models.articles.Article
import com.darrenthiores.kompastest.core.models.highlights.LiveReport
import com.darrenthiores.kompastest.core.models.highlights.MoreReports
import com.darrenthiores.kompastest.core_ui.decoration.DashedLine
import com.darrenthiores.kompastest.features.homepage.presentation.components.items.CompactArticleItem

@Composable
fun LiveReportBlockView(
    modifier: Modifier = Modifier,
    liveReport: LiveReport,
    onClick: (Article) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surface
            )
            .padding(bottom = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        // Hero
        LiveReportHero(
            modifier = Modifier,
            liveReport = liveReport,
            onClick = onClick
        )

        if (liveReport.featuredArticles.isNotEmpty()) {
            Column {
                liveReport.featuredArticles.forEach { article ->
                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 16.dp
                            ),
                        thickness = 1.dp,
                        color = GrayLight
                    )

                    CompactArticleItem(
                        modifier = Modifier,
                        article = article,
                        onClick = {
                            onClick(article)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun LiveReportHero(
    modifier: Modifier = Modifier,
    liveReport: LiveReport,
    onClick: (Article) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp),
            contentAlignment = Alignment.TopStart
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = GrayLight
                    ),
                model = liveReport.mainArticle?.imageUrl,
                contentDescription = liveReport.mainArticle?.imageDescription,
                contentScale = ContentScale.Crop
            )

            if (liveReport.reportType.isNotEmpty()) {
                Text(
                    modifier = Modifier
                        .padding(16.dp)
                        .background(
                            color = RedRegular,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(
                            vertical = 4.dp,
                            horizontal = 8.dp
                        ),
                    text = liveReport.reportType,
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = Color.White
                    )
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp
                ),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            liveReport.mainArticle?.category?.let { category ->
                Text(
                    text = category,
                    style = MaterialTheme.typography.labelLarge.copy(
                        color = RedRegular
                    )
                )
            }

            Text(
                modifier = Modifier
                    .clickable {
                        liveReport.mainArticle?.let { article ->
                            onClick(article)
                        }
                    },
                text = liveReport.mainArticle?.title ?: "-",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Medium
                )
            )

            liveReport.mainArticle?.publishedTime?.let { publishedTime ->
                Text(
                    text = publishedTime,
                    style = MaterialTheme.typography.labelLarge.copy(
                        color = GrayRegular
                    )
                )
            }

            if (liveReport.relatedArticles.isNotEmpty()) {
                Column {
                    liveReport.relatedArticles.forEachIndexed { index, article ->
                        ArticleTimelineItem(
                            modifier = Modifier,
                            article = article,
                            lastItem = index == liveReport.relatedArticles.lastIndex,
                            onClick = {
                                onClick(article)
                            }
                        )
                    }
                }
            }
        }

        liveReport.moreReports?.let { moreReports ->
            MoreReportsRow(
                modifier = Modifier,
                moreReports = moreReports
            )
        }
    }
}

@Composable
private fun MoreReportsRow(
    modifier: Modifier = Modifier,
    moreReports: MoreReports
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = moreReports.label,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium
                )
            )

            Text(
                modifier = Modifier
                    .background(
                        color = RedRegular,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(
                        vertical = 2.dp,
                        horizontal = 8.dp
                    ),
                text = moreReports.count,
                style = MaterialTheme.typography.labelMedium.copy(
                    color = Color.White
                )
            )
        }

        IconButton(
            onClick = {

            }
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = moreReports.label
            )
        }
    }
}

@Composable
private fun ArticleTimelineItem(
    modifier: Modifier = Modifier,
    article: Article,
    lastItem: Boolean = false,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(
                    top = 4.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .background(
                        color = RedRegular,
                        shape = CircleShape
                    )
                    .clip(CircleShape)
            )

            if (!lastItem) {
                DashedLine(
                    modifier = Modifier
                        .fillMaxHeight(),
                    color = GrayRegular,
                    dashLength = 5f,
                    gapLength = 5f
                )
            }
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .clickable {
                    onClick.invoke()
                }
                .padding(
                    bottom = if (lastItem) 0.dp
                    else 20.dp
                ),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.Start
        ) {
            article.publishedTime?.let { publishedTime ->
                Text(
                    text = publishedTime,
                    style = MaterialTheme.typography.labelLarge.copy(
                        color = GrayRegular
                    )
                )
            }

            Text(
                text = article.title,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}