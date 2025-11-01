package com.darrenthiores.kompastest.features.highlights.data.dto

import com.darrenthiores.kompastest.core.models.highlights.HotTopics
import kotlinx.serialization.Serializable

@Serializable
data class HotTopicsDto(
    val section: String? = null,
    val topics: List<TopicDto>? = null
) {
    fun toDomain(): HotTopics {
        return HotTopics(
            section = section.orEmpty(),
            topics = topics
                ?.map { it.toDomain() }
                .orEmpty()
        )
    }
}