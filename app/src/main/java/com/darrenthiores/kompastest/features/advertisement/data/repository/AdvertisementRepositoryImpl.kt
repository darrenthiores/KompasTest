package com.darrenthiores.kompastest.features.advertisement.data.repository

import com.darrenthiores.kompastest.core.models.advertisement.Ads
import com.darrenthiores.kompastest.core.models.advertisement.Campaign
import com.darrenthiores.kompastest.core.utils.ext.mapToResource
import com.darrenthiores.kompastest.core.utils.models.Resource
import com.darrenthiores.kompastest.features.advertisement.data.local.AdvertisementLocalDataSource
import com.darrenthiores.kompastest.features.advertisement.domain.repository.AdvertisementRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AdvertisementRepositoryImpl @Inject constructor(
    private val localDataSource: AdvertisementLocalDataSource
): AdvertisementRepository {
    override fun getAds(): Resource<Ads> {
        return localDataSource
            .getAds()
            .mapToResource { ads ->
                ads.toDomain()
            }
    }

    override fun getCampaign(): Resource<Campaign> {
        return localDataSource
            .getCampaign()
            .mapToResource { campaign ->
                campaign.toDomain()
            }
    }
}