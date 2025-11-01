package com.darrenthiores.kompastest.features.articles.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ArticlesPagingDto(
    val articles: List<ArticlePageDto>? = null
)