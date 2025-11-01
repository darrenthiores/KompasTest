package com.darrenthiores.kompastest.features.articles.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ArticlesDto(
    val articles: List<ArticleDto>? = null
)