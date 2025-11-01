package com.darrenthiores.kompastest.features.highlights.data.dto

import com.darrenthiores.kompastest.core.models.articles.Article
import com.darrenthiores.kompastest.core.models.highlights.BreakingNews
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BreakingNewsDto(
    val headline: String? = null,
    val subheadline: String? = null,
    @SerialName("published_time") val publishedTime: String? = null,
    val articles: List<ArticleDto>? = null,
    val source: String? = null
) {
    fun toDomain(): BreakingNews {
        return BreakingNews(
            headline = headline.orEmpty(),
            subheadline = subheadline.orEmpty(),
            publishedTime = publishedTime,
            articles = articles?.map { it.toDomain() }
                .orEmpty(),
            source = source
        )
    }

    @Serializable
    data class ArticleDto(
        val id: Int? = null,
        val title: String? = null,
        @SerialName("published_time") val publishedTime: String? = null
    ) {
        fun toDomain(): Article {
            return Article(
                id = id ?: -1,
                title = title.orEmpty(),
                publishedTime = publishedTime,
                description = "",
                imageUrl = null,
            )
        }
    }
}