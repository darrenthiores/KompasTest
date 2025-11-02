package com.darrenthiores.kompastest.features.articles.presentation.article_detail

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.darrenthiores.kompastest.app.navigation.routes.AppRoutes
import com.darrenthiores.kompastest.core_ui.bar.AppTopBar
import com.darrenthiores.kompastest.core_ui.empty.EmptyListView
import com.darrenthiores.kompastest.core_ui.error.ErrorListView
import com.darrenthiores.kompastest.core_ui.utils.LocalPadding
import com.darrenthiores.kompastest.core_ui.utils.encodeForRoute
import com.darrenthiores.kompastest.features.articles.presentation.article_detail.components.ArticleDetailContent
import com.darrenthiores.kompastest.features.articles.presentation.article_detail.components.ArticleDetailHero
import com.darrenthiores.kompastest.features.articles.presentation.article_detail.components.ArticleDetailReads
import com.darrenthiores.kompastest.features.articles.presentation.article_detail.components.ArticleDetailShimmer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleDetailScreen(
    state: ArticleDetailState,
    onEvent: (ArticleDetailEvent) -> Unit,
    onBack: () -> Unit,
    onNavigate: (String) -> Unit
) {
    val listState = rememberLazyListState()
    val padding = LocalPadding.current

    Scaffold (
        topBar = {
            Surface(
                shadowElevation = 2.dp,
                color = MaterialTheme.colorScheme.primary
            ) {
                AppTopBar(
                    leftActionItem = {
                        IconButton(
                            onClick = onBack
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        scrolledContainerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = Color.White,
                        actionIconContentColor = Color.White
                    ),
                )
            }
        },
    ) { scaffoldPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = scaffoldPadding.calculateTopPadding(),
                    bottom = padding.calculateBottomPadding()
                ),
            state = listState
        ) {
            state.articleState.data?.let { articleDetail ->
                item {
                    ArticleDetailHero(
                        title = state.title,
                        article = articleDetail,
                        onEvent = onEvent
                    )
                }

                item {
                    ArticleDetailContent(
                        modifier = Modifier,
                        article = articleDetail
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(12.dp))
                }

                item {
                    ArticleDetailReads(
                        modifier = Modifier,
                        article = articleDetail,
                        onClick = { article ->
                            val encodedTitle = article.title.encodeForRoute()

                            onNavigate(
                                AppRoutes.ARTICLE_DETAIL.name + "?title=${encodedTitle}" + "&article_id=${article.id}"
                            )
                        }
                    )
                }
            } ?: item {
                when {
                    state.articleState.isLoading -> {
                        ArticleDetailShimmer()
                    }
                    state.articleState.error != null -> {
                        ErrorListView(
                            onTryAgain = {
                                onEvent(ArticleDetailEvent.OnErrorTryAgain)
                            }
                        )
                    }
                    else -> {
                        EmptyListView()
                    }
                }
            }
        }
    }
}