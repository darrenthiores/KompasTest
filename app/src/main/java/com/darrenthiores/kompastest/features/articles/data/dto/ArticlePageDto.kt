package com.darrenthiores.kompastest.features.articles.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ArticlePageDto(
    val page: Int? = null,
    val data: List<ArticleDto>? = null
)