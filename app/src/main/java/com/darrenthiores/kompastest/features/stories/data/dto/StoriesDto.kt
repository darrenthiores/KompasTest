package com.darrenthiores.kompastest.features.stories.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class StoriesDto(
    val stories: List<StoryDto>? = null
)
