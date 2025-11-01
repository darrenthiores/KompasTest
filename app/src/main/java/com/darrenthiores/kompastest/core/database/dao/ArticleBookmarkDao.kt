package com.darrenthiores.kompastest.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.darrenthiores.kompastest.core.database.entity.ArticleBookmarkEntity

@Dao
interface ArticleBookmarkDao {
    @Query("SELECT id FROM ArticleBookmarkEntity")
    suspend fun getAllBookmarkedIds(): List<Int>

    @Insert(onConflict = OnConflictStrategy.Companion.IGNORE)
    suspend fun insert(bookmark: ArticleBookmarkEntity): Long

    @Query("DELETE FROM ArticleBookmarkEntity WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM ArticleBookmarkEntity WHERE id = :id)")
    suspend fun exists(id: Int): Boolean

    @Transaction
    suspend fun toggleBookmark(id: Int) {
        if (exists(id)) {
            deleteById(id)
        } else {
            insert(ArticleBookmarkEntity(id))
        }
    }
}