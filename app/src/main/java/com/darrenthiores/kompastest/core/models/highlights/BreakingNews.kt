package com.darrenthiores.kompastest.core.models.highlights

import com.darrenthiores.kompastest.core.models.articles.Article

data class BreakingNews(
    val headline: String,
    val subheadline: String,
    val publishedTime: String?,
    val articles: List<Article>,
    val source: String?
)