package com.darrenthiores.kompastest.features.stories.data.dto

import com.darrenthiores.kompastest.core.models.stories.Story
import kotlinx.serialization.Serializable

@Serializable
data class StoryDto(
    val section: String? = null,
    val articles: List<StoryArticleDto>? = null
) {
    fun toDomain(): Story {
        return Story(
            section = section,
            articles = articles?.map { it.toDomain() }
        )
    }
}