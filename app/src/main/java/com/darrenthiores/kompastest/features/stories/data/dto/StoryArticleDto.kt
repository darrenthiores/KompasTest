package com.darrenthiores.kompastest.features.stories.data.dto

import com.darrenthiores.kompastest.core.models.articles.Article
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StoryArticleDto(
    val id: Int? = null,
    val title: String? = null,
    val author: String? = null,
    val label: String? = null,
    val description: String? = null,
    @SerialName("image_url") val imageUrl: String? = null,
    @SerialName("image_description") val imageDescription: String? = null,
    @SerialName("media_count") val mediaCount: Int? = null,
    @SerialName("published_date") val publishedDate: String? = null
) {
    fun toDomain(): Article {
        return Article(
            id = id ?: -1,
            title = title.orEmpty(),
            description = description.orEmpty(),
            imageDescription = imageDescription,
            imageUrl = imageUrl,
            label = label,
            mediaCount = mediaCount,
            author = author,
        )
    }
}