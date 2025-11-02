package com.darrenthiores.kompastest.features.homepage.presentation.components.loading

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.darrenthiores.kompastest.core_ui.loading.ShimmerEffect

@Composable
fun HomeShimmer(
    modifier: Modifier = Modifier,
    count: Int = 2
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        repeat(count) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        bottom = 24.dp
                    ),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ShimmerEffect(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                )

                Column(
                    modifier = Modifier
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
                        ShimmerEffect(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                        )

                        ShimmerEffect(
                            modifier = Modifier
                                .fillMaxWidth(fraction = 0.8f)
                                .height(48.dp)
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        repeat(2) {
                            ShimmerEffect(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(32.dp)
                            )
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        ShimmerEffect(
                            modifier = Modifier
                                .fillMaxWidth(fraction = 0.25f)
                                .height(16.dp)
                        )

                        Row(
                            modifier = Modifier,
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            repeat(3) {
                                ShimmerEffect(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clip(CircleShape)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}