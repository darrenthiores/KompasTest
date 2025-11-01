package com.darrenthiores.kompastest.features.advertisement.data.local

import com.darrenthiores.kompastest.core.utils.parser.LocalJsonParser
import com.darrenthiores.kompastest.features.advertisement.data.dto.AdsDto
import com.darrenthiores.kompastest.features.advertisement.data.dto.CampaignDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AdvertisementLocalDataSource @Inject constructor(
    private val parser: LocalJsonParser
) {
    fun getAds(): Result<AdsDto> {
        return parser.loadJson<AdsDto>("files/ads.json")
    }

    fun getCampaign(): Result<CampaignDto> {
        return parser.loadJson<CampaignDto>("files/campaign.json")
    }
}