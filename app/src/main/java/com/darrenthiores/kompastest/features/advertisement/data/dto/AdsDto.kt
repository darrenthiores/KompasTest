package com.darrenthiores.kompastest.features.advertisement.data.dto

import com.darrenthiores.kompastest.core.models.advertisement.Ads
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AdsDto(
    val url: String? = null,
    @SerialName("image_path") val imagePath: String? = null
) {
    fun toDomain(): Ads {
        return Ads(
            url = url.orEmpty(),
            imagePath = imagePath.orEmpty()
        )
    }
}