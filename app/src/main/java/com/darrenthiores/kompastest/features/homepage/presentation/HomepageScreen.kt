package com.darrenthiores.kompastest.features.homepage.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.darrenthiores.kompastest.app.navigation.routes.AppRoutes
import com.darrenthiores.kompastest.app.theme.GrayLight
import com.darrenthiores.kompastest.core_ui.bottom_sheet.ShareArticleBottomSheet
import com.darrenthiores.kompastest.core_ui.empty.EmptyListView
import com.darrenthiores.kompastest.core_ui.error.ErrorListView
import com.darrenthiores.kompastest.core_ui.utils.LocalPadding
import com.darrenthiores.kompastest.core_ui.utils.encodeForRoute
import com.darrenthiores.kompastest.features.homepage.domain.models.HomepageBlock
import com.darrenthiores.kompastest.features.homepage.presentation.components.bar.HomeTopBar
import com.darrenthiores.kompastest.features.homepage.presentation.components.blocks.AdsBlockView
import com.darrenthiores.kompastest.features.homepage.presentation.components.blocks.ArticlesBlockView
import com.darrenthiores.kompastest.features.homepage.presentation.components.blocks.BreakingNewsBlockView
import com.darrenthiores.kompastest.features.homepage.presentation.components.blocks.CampaignBlockView
import com.darrenthiores.kompastest.features.homepage.presentation.components.blocks.HotTopicsBlockView
import com.darrenthiores.kompastest.features.homepage.presentation.components.blocks.LiveReportBlockView
import com.darrenthiores.kompastest.features.homepage.presentation.components.blocks.StoryBlockView
import com.darrenthiores.kompastest.features.homepage.presentation.components.loading.HomeShimmer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomepageScreen(
    state: HomepageState,
    onEvent: (HomepageEvent) -> Unit,
    onNavigate: (String) -> Unit
) {
    val topAppBarState = rememberTopAppBarState()
    val pinnedScroll = TopAppBarDefaults.pinnedScrollBehavior(
        state = topAppBarState
    )
    val enterAlways = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = topAppBarState
    )
    val scrollBehavior = remember(state.homePagingState.items) {
        if (state.homePagingState.items.isEmpty()) pinnedScroll
        else enterAlways
    }
    val listState = rememberLazyListState()
    val itemReachEnd = remember { mutableStateOf(false) }
    val padding = LocalPadding.current
    val pullToRefreshState = rememberPullToRefreshState()

    LaunchedEffect(listState, state.homePagingState.items) {
        snapshotFlow {
            listState.layoutInfo.visibleItemsInfo
        }.collect {
            val lastVisibleKey = it.lastOrNull()?.key ?: return@collect
            val blocks = state
                .homePagingState
                .items
                .lastOrNull()
            val lastBlockKey = blocks?.id

            itemReachEnd.value = lastVisibleKey == lastBlockKey
        }
    }

    LaunchedEffect(itemReachEnd.value) {
        if (itemReachEnd.value) {
            onEvent(
                HomepageEvent.OnEndOfListReached
            )
        }
    }

    PullToRefreshBox(
        modifier = Modifier,
        state = pullToRefreshState,
        isRefreshing = state.isRefreshing,
        onRefresh = {
            onEvent(HomepageEvent.OnPullToRefresh)
        },
        indicator = {
            Indicator(
                modifier = Modifier
                    .align(Alignment.TopCenter),
                isRefreshing = state.isRefreshing,
                containerColor = MaterialTheme.colorScheme.surface,
                color = MaterialTheme.colorScheme.onSurface,
                state = pullToRefreshState
            )
        }
    )
    {
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                HomeTopBar(
                    modifier = Modifier,
                    scrollBehavior = scrollBehavior
                )
            },
        ) { scaffoldPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = scaffoldPadding.calculateTopPadding(),
                        bottom = padding.calculateBottomPadding()
                    )
                ,
                state = listState,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                when {
                    state.homePagingState.items.isEmpty() -> {
                        when {
                            state.homePagingState.isLoading -> {
                                item {
                                    HomeShimmer()
                                }
                            }
                            state.homePagingState.error != null -> {
                                item {
                                    ErrorListView(
                                        onTryAgain = {
                                            onEvent(HomepageEvent.OnErrorTryAgain)
                                        }
                                    )
                                }
                            }
                            else -> {
                                item {
                                    EmptyListView()
                                }
                            }
                        }
                    }
                    else -> {
                        items(
                            items = state.homePagingState.items,
                            key = { block -> block.id }
                        ) { block ->
                            when (block) {
                                is HomepageBlock.AdsBlock -> {
                                    AdsBlockView(ads = block.ads)
                                }
                                is HomepageBlock.ArticlesBlock -> {
                                    ArticlesBlockView(
                                        articles = block.articles,
                                        onClick = { article ->
                                            val encodedTitle = article.title.encodeForRoute()

                                            onNavigate(
                                                AppRoutes.ARTICLE_DETAIL.name + "?title=${encodedTitle}" + "&article_id=${article.id}"
                                            )
                                        },
                                        onClickShare = { article ->
                                            onEvent(HomepageEvent.ToggleShareArticle(article))
                                        },
                                        onBookmark = { article ->
                                            onEvent(
                                                HomepageEvent.BookmarkArticle(
                                                    block = block,
                                                    articleId = article.id
                                                )
                                            )
                                        }
                                    )
                                }
                                is HomepageBlock.BreakingNewsBlock -> {
                                    BreakingNewsBlockView(
                                        breakingNews = block.breakingNews,
                                        onClick = { article ->
                                            val encodedTitle = article.title.encodeForRoute()

                                            onNavigate(
                                                AppRoutes.ARTICLE_DETAIL.name + "?title=${encodedTitle}" + "&article_id=${article.id}"
                                            )
                                        },
                                        onClickShare = { article ->
                                            onEvent(HomepageEvent.ToggleShareArticle(article))
                                        },
                                        onBookmark = { article ->
                                            onEvent(
                                                HomepageEvent.BookmarkArticle(
                                                    block = block,
                                                    articleId = article.id
                                                )
                                            )
                                        }
                                    )
                                }
                                is HomepageBlock.CampaignBlock -> {
                                    CampaignBlockView(campaign = block.campaign)
                                }
                                is HomepageBlock.HotTopicsBlock -> {
                                    HotTopicsBlockView(hotTopics = block.hotTopics)
                                }
                                is HomepageBlock.LiveReportBlock -> {
                                    LiveReportBlockView(
                                        liveReport = block.liveReport,
                                        onClick = { article ->
                                            val encodedTitle = article.title.encodeForRoute()

                                            onNavigate(
                                                AppRoutes.ARTICLE_DETAIL.name + "?title=${encodedTitle}" + "&article_id=${article.id}"
                                            )
                                        },
                                    )
                                }
                                is HomepageBlock.StoryBlock -> {
                                    StoryBlockView(
                                        story = block.story,
                                        onClick = { article ->
                                            val encodedTitle = article.title.encodeForRoute()

                                            onNavigate(
                                                AppRoutes.ARTICLE_DETAIL.name + "?title=${encodedTitle}" + "&article_id=${article.id}"
                                            )
                                        },
                                        onClickShare = { article ->
                                            onEvent(HomepageEvent.ToggleShareArticle(article))
                                        },
                                        onBookmark = { article ->
                                            onEvent(
                                                HomepageEvent.BookmarkArticle(
                                                    block = block,
                                                    articleId = article.id
                                                )
                                            )
                                        }
                                    )
                                }
                            }
                        }

                        if (state.homePagingState.isLoading) {
                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            vertical = 24.dp
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.size(18.dp),
                                        color = MaterialTheme.colorScheme.onSurface,
                                        trackColor = GrayLight.copy(
                                            alpha = 0.5f
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    state.selectedArticle?.let { article ->
        ShareArticleBottomSheet(
            modifier = Modifier,
            article = article,
            onDismiss = {
                onEvent(HomepageEvent.ToggleShareArticle(null))
            }
        )
    }
}