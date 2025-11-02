package com.darrenthiores.kompastest.core_ui.bar

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import com.darrenthiores.kompastest.app.navigation.routes.TopLevelDestination
import com.darrenthiores.kompastest.app.theme.GrayRegular
import com.darrenthiores.kompastest.core_ui.utils.topElevation

@Composable
fun AppBottomBar(
    modifier: Modifier = Modifier,
    currentDestination: NavDestination?,
    onTabSelected: (TopLevelDestination) -> Unit,
) {
    val currentTopLevelDestination = remember(currentDestination) {
        TopLevelDestination.fromRoute(
            currentDestination?.route
        )
    }
    val currentParent = remember(currentDestination) {
        TopLevelDestination.fromRoute(
            currentDestination?.parent?.route
        )
    }

    Surface(
        modifier = modifier
            .topElevation(
                elevation = 16.dp
            ),
        shadowElevation = 16.dp,
    ) {
        NavigationBar(
            modifier = modifier,
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ) {
            TopLevelDestination.entries.forEach { destination ->
                NavigationBarItem(
                    selected = currentTopLevelDestination == destination || currentParent == destination,
                    onClick = {
                        onTabSelected(destination)
                    },
                    icon = {
                        Icon(
                            destination.icon,
                            contentDescription = destination.title
                        )
                    },
                    label = { Text(destination.title) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                        unselectedIconColor = GrayRegular,
                        unselectedTextColor = GrayRegular,
                        indicatorColor = Color.Transparent
                    )
                )
            }
        }
    }
}