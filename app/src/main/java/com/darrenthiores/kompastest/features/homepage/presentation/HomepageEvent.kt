package com.darrenthiores.kompastest.features.homepage.presentation

sealed interface HomepageEvent {
    data object OnEndOfListReached: HomepageEvent
    data object OnPullToRefresh: HomepageEvent
    data object OnErrorTryAgain: HomepageEvent
}