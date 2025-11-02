package com.darrenthiores.kompastest.features.articles.presentation.article_detail

import com.darrenthiores.kompastest.core.utils.models.DataState
import com.darrenthiores.kompastest.features.articles.domain.models.ArticleDetail

data class ArticleDetailState(
    val title: String? = null,
    val articleState: DataState<ArticleDetail, String> = DataState(),
    val shareBottomSheetOpen: Boolean = false,
    val audioIsPlaying: Boolean = false,
    val audioIsLoading: Boolean = false,
)
