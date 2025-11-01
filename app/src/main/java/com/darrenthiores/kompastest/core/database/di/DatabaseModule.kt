package com.darrenthiores.kompastest.core.database.di

import android.content.Context
import androidx.room.Room
import com.darrenthiores.kompastest.core.database.KompasDatabase
import com.darrenthiores.kompastest.core.database.dao.ArticleBookmarkDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): KompasDatabase =
        Room
            .databaseBuilder(
                context,
                KompasDatabase::class.java,
                "kompas.db"
            )
            .fallbackToDestructiveMigration(false)
            .build()

    @Provides
    fun provideArticleBookmarkDao(
        db: KompasDatabase
    ): ArticleBookmarkDao {
        return db.articleBookmarkDao()
    }
}