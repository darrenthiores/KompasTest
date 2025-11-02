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
    val publishedTime: String?,
    val relatedArticles: List<Article>,
    val bookmarked: Boolean = false
) {
    fun toArticle(): Article {
        return Article(
            id = id ?: -1,
            title = title,
            description = description,
            imageDescription = imageDescription,
            imageUrl = imageUrl,
            label = label,
            publishedTime = publishedTime,
            author = author,
            bookmarked = bookmarked
        )
    }
}
