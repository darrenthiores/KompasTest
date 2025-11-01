package com.darrenthiores.kompastest.features.articles.di

import com.darrenthiores.kompastest.features.articles.data.repository.ArticleRepositoryImpl
import com.darrenthiores.kompastest.features.articles.domain.repository.ArticleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ArticleRepositoryModule {
    @Binds
    abstract fun provideArticleRepository(
        repository: ArticleRepositoryImpl
    ): ArticleRepository
}