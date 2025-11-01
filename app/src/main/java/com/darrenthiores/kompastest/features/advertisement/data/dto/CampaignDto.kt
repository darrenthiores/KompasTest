package com.darrenthiores.kompastest.features.advertisement.data.dto

import com.darrenthiores.kompastest.core.models.advertisement.Campaign
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CampaignDto(
    val title: String? = null,
    val url: String? = null,
    @SerialName("image_path") val imagePath: String? = null
) {
    fun toDomain(): Campaign {
        return Campaign(
            title = title.orEmpty(),
            url = url.orEmpty(),
            imagePath = imagePath.orEmpty()
        )
    }
}