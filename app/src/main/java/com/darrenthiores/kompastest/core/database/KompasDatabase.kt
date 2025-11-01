package com.darrenthiores.kompastest.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.darrenthiores.kompastest.core.database.dao.ArticleBookmarkDao
import com.darrenthiores.kompastest.core.database.entity.ArticleBookmarkEntity

@Database(
    entities = [ArticleBookmarkEntity::class],
    version = 1,
    exportSchema = false
)
abstract class KompasDatabase: RoomDatabase() {
    abstract fun articleBookmarkDao(): ArticleBookmarkDao
}