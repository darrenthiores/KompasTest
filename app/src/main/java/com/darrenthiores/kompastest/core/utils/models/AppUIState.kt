package com.darrenthiores.kompastest.core.utils.models

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.darrenthiores.kompastest.app.navigation.routes.TopLevelDestination
import kotlinx.coroutines.CoroutineScope

class AppUIState(
    val navController: NavHostController,
    private val coroutineScope: CoroutineScope
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val shouldShowBottomBar: Boolean
        @Composable get() = when (currentDestination?.route) {
            TopLevelDestination.HOME.name,
            TopLevelDestination.PAPER.name,
            TopLevelDestination.PUZZLE.name,
            TopLevelDestination.BOOK.name,
            TopLevelDestination.ACCOUNT.name -> true
            else -> false
        }
}

@Composable
fun rememberAppUIState(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
): AppUIState = remember(navController) {
    AppUIState(
        navController,
        coroutineScope
    )
}