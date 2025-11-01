package com.darrenthiores.kompastest.features.highlights.data.dto

import com.darrenthiores.kompastest.core.models.highlights.Topic
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopicDto(
    val title: String? = null,
    @SerialName("image_url") val imageUrl: String? = null,
    @SerialName("image_description") val imageDescription: String? = null
) {
    fun toDomain(): Topic {
        return Topic(
            title = title.orEmpty(),
            imageUrl = imageUrl,
            imageDescription = imageDescription
        )
    }
}