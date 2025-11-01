package com.darrenthiores.kompastest.features.advertisement.di

import com.darrenthiores.kompastest.features.advertisement.data.repository.AdvertisementRepositoryImpl
import com.darrenthiores.kompastest.features.advertisement.domain.repository.AdvertisementRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AdvertisementRepositoryModule {
    @Binds
    abstract fun provideAdvertisementRepository(
        repository: AdvertisementRepositoryImpl
    ): AdvertisementRepository
}
