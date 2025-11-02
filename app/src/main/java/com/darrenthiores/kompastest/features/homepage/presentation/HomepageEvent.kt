package com.darrenthiores.kompastest.features.homepage.presentation

import com.darrenthiores.kompastest.core.models.articles.Article
import com.darrenthiores.kompastest.features.homepage.domain.models.HomepageBlock

sealed interface HomepageEvent {
    data object OnEndOfListReached: HomepageEvent
    data object OnPullToRefresh: HomepageEvent
    data object OnErrorTryAgain: HomepageEvent
    data class ToggleShareArticle(
        val article: Article?
    ): HomepageEvent
    data class BookmarkArticle(
        val block: HomepageBlock,
        val articleId: Int
    ): HomepageEvent
}