package com.darrenthiores.kompastest.features.homepage.presentation.components.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.darrenthiores.kompastest.app.theme.GrayRegular
import com.darrenthiores.kompastest.core.models.articles.Article
import com.darrenthiores.kompastest.core_ui.row.ArticleUtilityRow

@Composable
fun FullArticleItem(
    modifier: Modifier = Modifier,
    article: Article?
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp
            ),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = article?.title ?: "-",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Medium,
                )
            )

            Text(
                text = article?.description ?: "-",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Normal,
                    color = GrayRegular
                )
            )
        }

        ArticleUtilityRow(
            modifier = Modifier
                .align(Alignment.End),
            onClickShare = { },
            isBookmarked = article?.bookmarked ?: false,
            onBookmark = { },
            isAudioActive = false,
            onClickAudio = { },
        )
    }
}