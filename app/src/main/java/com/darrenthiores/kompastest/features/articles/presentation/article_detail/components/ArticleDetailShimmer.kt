package com.darrenthiores.kompastest.features.articles.presentation.article_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.darrenthiores.kompastest.core_ui.loading.ShimmerEffect

@Composable
fun ArticleDetailShimmer(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        ShimmerEffect(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp
                )
                .padding(
                    bottom = 24.dp
                ),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ShimmerEffect(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                )

                ShimmerEffect(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                )
            }

            ShimmerEffect(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
            )
        }
    }
}