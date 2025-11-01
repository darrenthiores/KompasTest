package com.darrenthiores.kompastest.features.bookmark.di

import com.darrenthiores.kompastest.features.bookmark.data.repository.BookmarkRepositoryImpl
import com.darrenthiores.kompastest.features.bookmark.domain.repository.BookmarkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BookmarkRepositoryModule {
    @Binds
    abstract fun provideBookmarkRepository(
        repository: BookmarkRepositoryImpl
    ): BookmarkRepository
}