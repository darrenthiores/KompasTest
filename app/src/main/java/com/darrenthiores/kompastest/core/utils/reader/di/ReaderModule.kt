package com.darrenthiores.kompastest.core.utils.reader.di

import android.content.Context
import android.content.res.AssetManager
import com.darrenthiores.kompastest.core.utils.reader.assets.AndroidAssetReader
import com.darrenthiores.kompastest.core.utils.reader.assets.AssetReader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ReaderModule {

    @Provides
    @Singleton
    fun provideAssetManager(
        @ApplicationContext context: Context
    ): AssetManager = context.assets

    @Provides
    @Singleton
    fun provideAssetReader(
        androidAssetReader: AndroidAssetReader
    ): AssetReader = androidAssetReader
}