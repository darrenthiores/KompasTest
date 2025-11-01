package com.darrenthiores.kompastest.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArticleBookmarkEntity(
    @PrimaryKey(autoGenerate = false) val id: Int
)