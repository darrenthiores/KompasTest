package com.darrenthiores.kompastest.features.highlights.di

import com.darrenthiores.kompastest.features.highlights.data.repository.HighlightRepositoryImpl
import com.darrenthiores.kompastest.features.highlights.domain.repository.HighlightRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class HighlightRepositoryModule {
    @Binds
    abstract fun provideHighlightRepository(
        repository: HighlightRepositoryImpl
    ): HighlightRepository
}
