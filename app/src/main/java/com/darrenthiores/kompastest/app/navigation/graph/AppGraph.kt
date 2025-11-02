package com.darrenthiores.kompastest.app.navigation.graph

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.darrenthiores.kompastest.app.navigation.routes.AppRoutes
import com.darrenthiores.kompastest.app.navigation.routes.TopLevelDestination
import com.darrenthiores.kompastest.core.utils.models.AppUIState
import com.darrenthiores.kompastest.core.utils.models.rememberAppUIState
import com.darrenthiores.kompastest.core_ui.bar.AppBottomBar
import com.darrenthiores.kompastest.core_ui.utils.DynamicStatusBarColor
import com.darrenthiores.kompastest.core_ui.utils.LocalPadding
import com.darrenthiores.kompastest.features.articles.presentation.article_detail.ArticleDetailScreen
import com.darrenthiores.kompastest.features.articles.presentation.article_detail.ArticleDetailViewModel
import com.darrenthiores.kompastest.features.homepage.presentation.HomepageScreen
import com.darrenthiores.kompastest.features.homepage.presentation.HomepageViewModel

@Composable
fun AppGraph(
    appUIState: AppUIState = rememberAppUIState(),
    startDestination: String = TopLevelDestination.HOME.name,
) {
    val navController = remember(appUIState) {
        appUIState.navController
    }
    var isAppearanceLight by remember {
        mutableStateOf(false)
    }

    @Composable
    fun UpdateAppearance(appearanceLight: Boolean = false) {
        LaunchedEffect(true) {
            isAppearanceLight = appearanceLight
        }
    }

    DynamicStatusBarColor(
        isAppearanceLightStatusBars = isAppearanceLight
    )

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {
            if (appUIState.shouldShowBottomBar) {
                AppBottomBar(
                    currentDestination = appUIState.currentDestination,
                    onTabSelected = {
                        navController.navigate(it.name)
                    }
                )
            }
        },
    ) { scaffoldPadding ->
        CompositionLocalProvider(LocalPadding provides scaffoldPadding) {
            NavHost(
                modifier = Modifier,
                navController = navController,
                startDestination = startDestination
            ) {
                composable(
                    route = TopLevelDestination.HOME.name
                ) {
                    val viewModel = hiltViewModel<HomepageViewModel>()
                    val state by viewModel.state.collectAsState()

                    UpdateAppearance()

                    LaunchedEffect(true) {
                        navController.navigate(
                            AppRoutes.ARTICLE_DETAIL.name + "?title=&article_id=1"
                        )
                    }

                    HomepageScreen(
                        state = state,
                        onEvent = viewModel::onEvent,
                        onNavigate = { route ->
                            navController.navigate(route)
                        }
                    )
                }

                composable(
                    route = TopLevelDestination.PAPER.name
                ) {
                    UpdateAppearance(appearanceLight = true)
                }

                composable(
                    route = TopLevelDestination.PUZZLE.name
                ) {
                    UpdateAppearance(appearanceLight = true)
                }

                composable(
                    route = TopLevelDestination.BOOK.name
                ) {
                    UpdateAppearance(appearanceLight = true)
                }

                composable(
                    route = TopLevelDestination.ACCOUNT.name
                ) {
                    UpdateAppearance(appearanceLight = true)
                }

                composable(
                    route = AppRoutes.ARTICLE_DETAIL.name + "?title={title}&article_id={article_id}",
                    arguments = listOf(
                        navArgument("title") {
                            type = NavType.StringType
                        },
                        navArgument("article_id") {
                            type = NavType.IntType
                        }
                    )
                ) { _ ->
                    val viewModel = hiltViewModel<ArticleDetailViewModel>()
                    val state by viewModel.state.collectAsState()

                    UpdateAppearance(appearanceLight = false)

                    ArticleDetailScreen(
                        state = state,
                        onEvent = viewModel::onEvent,
                        onBack = {
                            navController.navigateUp()
                        },
                        onNavigate = { route ->
                            navController.navigate(route)
                        }
                    )
                }
            }
        }
    }
}