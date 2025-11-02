package com.darrenthiores.kompastest.features.articles.domain.models

import com.darrenthiores.kompastest.core.models.articles.Article

data class ArticleDetail(
    val id: Int?,
    val title: String,
    val description: String,
    val content: String,
    val imageDescription: String?,
    val imageUrl: String,
    val label: String?,
    val author: String,
    val publishedDate: String?,
    val relatedArticles: List<Article>,
    val bookmarked: Boolean = false
)
