package com.darrenthiores.kompastest.features.bookmark.data.local

import com.darrenthiores.kompastest.core.database.dao.ArticleBookmarkDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookmarkLocalDataSource @Inject constructor(
    private val dao: ArticleBookmarkDao
) {
    suspend fun getAllBookmarkedIds(): List<Int> {
        return dao.getAllBookmarkedIds()
    }

    suspend fun bookmarkArticle(id: Int) {
        dao.toggleBookmark(
            id = id
        )
    }

    suspend fun checkIsArticleBookmarked(id: Int): Boolean {
        return dao.exists(
            id = id
        )
    }
}