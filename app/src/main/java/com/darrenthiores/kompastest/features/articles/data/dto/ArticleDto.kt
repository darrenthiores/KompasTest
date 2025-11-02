package com.darrenthiores.kompastest.features.articles.data.dto

import com.darrenthiores.kompastest.core.models.articles.Article
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleDto(
    val id: Int? = null,
    val title: String? = null,
    val description: String? = null,
    @SerialName("image_description") val imageDescription: String? = null,
    @SerialName("image_url") val imageUrl: String? = null,
    val label: String? = null,
    @SerialName("published_time") val publishedTime: String? = null
) {
    fun toDomain(): Article? {
        return Article(
            id = id ?: return null,
            title = title.orEmpty(),
            description = description.orEmpty(),
            imageDescription = imageDescription,
            imageUrl = imageUrl,
            label = label,
            publishedTime = publishedTime
        )
    }
}