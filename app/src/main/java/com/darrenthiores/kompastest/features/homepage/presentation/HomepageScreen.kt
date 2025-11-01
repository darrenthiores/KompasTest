package com.darrenthiores.kompastest.features.homepage.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import com.darrenthiores.kompastest.core_ui.utils.LocalPadding
import com.darrenthiores.kompastest.features.homepage.presentation.components.CategoryRow
import com.darrenthiores.kompastest.features.homepage.presentation.components.HomeTopBar
import com.darrenthiores.kompastest.features.homepage.presentation.models.HomeCategory

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
    ) {
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                Surface(
                    shadowElevation = 2.dp,
                    color = MaterialTheme.colorScheme.primary
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Surface(
                            shadowElevation = 2.dp,
                            color = MaterialTheme.colorScheme.primary
                        ) {
                            HomeTopBar(
                                modifier = Modifier,
                                scrollBehavior = scrollBehavior
                            )
                        }

                        CategoryRow(
                            modifier = Modifier,
                            selectedCategory = HomeCategory.MAIN,
                            onSelectCategory = { },
                        )
                    }
                }
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
                state = listState
            ) {
                when {
                    state.homePagingState.items.isEmpty() -> {
                        when {
                            state.homePagingState.isLoading -> {

                            }
                            state.homePagingState.error != null -> {

                            }
                            else -> {

                            }
                        }
                    }
                    else -> {
                        items(
                            items = state.homePagingState.items,
                            key = { block -> block.id }
                        ) { block ->

                        }
                    }
                }
            }
        }
    }
}