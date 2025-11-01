package com.darrenthiores.kompastest.core.models.articles

data class Article(
    val id: Int,
    val title: String,
    val description: String,
    val imageDescription: String? = null,
    val imageUrl: String?,
    val label: String? = null,
    val mediaCount: Int? = null,
    val publishedTime: String? = null,
    val author: String? = null,
    val category: String? = null,
    val bookmarked: Boolean = false
)