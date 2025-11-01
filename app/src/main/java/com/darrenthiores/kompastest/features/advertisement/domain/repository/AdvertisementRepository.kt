package com.darrenthiores.kompastest.features.advertisement.domain.repository

import com.darrenthiores.kompastest.core.models.advertisement.Ads
import com.darrenthiores.kompastest.core.models.advertisement.Campaign
import com.darrenthiores.kompastest.core.utils.models.Resource

interface AdvertisementRepository {
    fun getAds(): Resource<Ads>
    fun getCampaign(): Resource<Campaign>
}