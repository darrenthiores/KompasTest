package com.darrenthiores.kompastest.features.stories.di

import com.darrenthiores.kompastest.features.stories.data.repository.StoryRepositoryImpl
import com.darrenthiores.kompastest.features.stories.domain.repository.StoryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class StoryRepositoryModule {
    @Binds
    abstract fun provideStoryRepository(
        repository: StoryRepositoryImpl
    ): StoryRepository
}
