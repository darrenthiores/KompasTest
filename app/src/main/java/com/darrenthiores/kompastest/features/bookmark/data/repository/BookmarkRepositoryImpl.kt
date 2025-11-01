package com.darrenthiores.kompastest.features.bookmark.data.repository

import com.darrenthiores.kompastest.features.bookmark.data.local.BookmarkLocalDataSource
import com.darrenthiores.kompastest.features.bookmark.domain.repository.BookmarkRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookmarkRepositoryImpl @Inject constructor(
    private val localDataSource: BookmarkLocalDataSource
): BookmarkRepository {
    override suspend fun getAllBookmarkedIds(): List<Int> {
        return localDataSource.getAllBookmarkedIds()
    }

    override suspend fun bookmarkArticle(id: Int) {
        localDataSource.bookmarkArticle(
            id = id
        )
    }

    override suspend fun checkIsArticleBookmarked(id: Int): Boolean {
        return localDataSource.checkIsArticleBookmarked(
            id = id
        )
    }
}