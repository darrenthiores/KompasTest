package com.darrenthiores.kompastest.core.models.stories

import com.darrenthiores.kompastest.core.models.articles.Article

data class Story(
    val section: String? = null,
    val articles: List<Article>? = null
)