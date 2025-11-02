package com.darrenthiores.kompastest.features.articles.presentation.article_detail

sealed interface ArticleDetailEvent {
    data object OnErrorTryAgain: ArticleDetailEvent
    data object Bookmark: ArticleDetailEvent
    data object ToggleBottomSheet: ArticleDetailEvent
    data object ToggleAudioPlayer: ArticleDetailEvent
}