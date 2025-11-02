package com.darrenthiores.kompastest.features.homepage.presentation

import com.darrenthiores.kompastest.core.models.articles.Article
import com.darrenthiores.kompastest.core_ui.paging.PagingState
import com.darrenthiores.kompastest.features.homepage.domain.models.HomepageBlock

data class HomepageState(
    val homePagingState: PagingState<Int, HomepageBlock> = PagingState(),
    val selectedArticle: Article? = null,
    val isRefreshing: Boolean = false
)
