package com.darrenthiores.kompastest.features.bookmark.domain.repository

interface BookmarkRepository {
    suspend fun getAllBookmarkedIds(): List<Int>
    suspend fun bookmarkArticle(id: Int)
    suspend fun checkIsArticleBookmarked(id: Int): Boolean
}