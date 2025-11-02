package com.darrenthiores.kompastest.features.articles.presentation.article_detail.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.darrenthiores.kompastest.app.theme.GrayRegular
import com.darrenthiores.kompastest.features.articles.domain.models.ArticleDetail

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun ArticleDetailContent(
    modifier: Modifier = Modifier,
    article: ArticleDetail
) {
    val contentInList = remember(article.content) {
        article.content.split("\n")
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surface
            )
            .padding(
                horizontal = 16.dp,
                vertical = 24.dp
            ),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = buildAnnotatedString {
                    append("Oleh ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(article.author.ifEmpty { "-" })
                    }
                },
                style = MaterialTheme.typography.labelLarge
            )

            article.publishedDate?.let { publishedDate ->
                Text(
                    text = publishedDate,
                    style = MaterialTheme.typography.labelLarge.copy(
                        color = GrayRegular
                    )
                )
            }
        }

        Column(
             modifier = Modifier
                 .fillMaxWidth(),
        ) {
            contentInList.forEach { content ->
                Text(
                    text = content,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}