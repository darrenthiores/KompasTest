package com.darrenthiores.kompastest.features.articles.data.dto

import com.darrenthiores.kompastest.features.articles.domain.models.ArticleDetail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleDetailDto(
    val id: Int? = null,
    val title: String? = null,
    val description: String? = null,
    val content: String? = null,
    @SerialName("image_description") val imageDescription: String? = null,
    @SerialName("image_url") val imageUrl: String? = null,
    val label: String? = null,
    val author: String? = null,
    @SerialName("published_time") val publishedTime: String? = null,
    @SerialName("related_articles") val relatedArticles: List<ArticleDto>? = null
) {
    fun toDomain(): ArticleDetail {
        return ArticleDetail(
            id = id,
            title = title.orEmpty(),
            description = description.orEmpty(),
            content = content.orEmpty(),
            imageDescription = imageDescription,
            imageUrl = imageUrl.orEmpty(),
            label = label,
            author = author.orEmpty(),
            publishedTime = publishedTime,
            relatedArticles = relatedArticles
                ?.mapNotNull { it.toDomain() }
                .orEmpty()
        )
    }
}