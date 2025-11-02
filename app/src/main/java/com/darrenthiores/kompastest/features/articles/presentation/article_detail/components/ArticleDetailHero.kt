package com.darrenthiores.kompastest.features.articles.presentation.article_detail.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.darrenthiores.kompastest.app.theme.GrayLight
import com.darrenthiores.kompastest.core_ui.row.ArticleUtilityRow
import com.darrenthiores.kompastest.features.articles.domain.models.ArticleDetail
import com.darrenthiores.kompastest.features.articles.presentation.article_detail.ArticleDetailEvent

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun ArticleDetailHero(
    modifier: Modifier = Modifier,
    title: String?,
    article: ArticleDetail,
    onEvent: (ArticleDetailEvent) -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp - TopAppBarDefaults.MediumAppBarCollapsedHeight

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(screenHeight),
        contentAlignment = Alignment.BottomStart
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(screenHeight)
                .background(
                    color = GrayLight
                ),
            model = article.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(screenHeight)
                .background(
                    color = Color.Black.copy(
                        alpha = 0.2f
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = title?.ifEmpty { article.title } ?: article.title,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                )

                Text(
                    text = article.description,
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Normal
                    )
                )

                ArticleUtilityRow(
                    modifier = Modifier,
                    onClickShare = { },
                    isBookmarked = article.bookmarked,
                    onBookmark = {
                        onEvent(ArticleDetailEvent.Bookmark)
                    },
                    isAudioActive = false,
                    onClickAudio = {  },
                    color = Color.White
                )
            }

            Icon(
                modifier = Modifier
                    .size(32.dp),
                imageVector = Icons.Default.ArrowCircleDown,
                contentDescription = "Scroll to read",
                tint = Color.White
            )
        }
    }
}