package com.darrenthiores.kompastest

import com.darrenthiores.kompastest.features.bookmark.data.local.BookmarkLocalDataSource
import com.darrenthiores.kompastest.features.bookmark.data.repository.BookmarkRepositoryImpl
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.junit.Assert.*

class BookmarkRepositoryImplTest {
    private lateinit var localDataSource: BookmarkLocalDataSource
    private lateinit var repository: BookmarkRepositoryImpl

    @Before
    fun setup() {
        localDataSource = mock()
        repository = BookmarkRepositoryImpl(localDataSource)
    }

    @Test
    fun `getAllBookmarkedIds should return list from localDataSource`() = runTest {
        val expected = listOf(10, 20, 30)
        whenever(localDataSource.getAllBookmarkedIds()).thenReturn(expected)

        val result = repository.getAllBookmarkedIds()

        assertEquals(expected, result)
        verify(localDataSource).getAllBookmarkedIds()
    }

    @Test
    fun `bookmarkArticle should call localDataSource bookmarkArticle`() = runTest {
        val articleId = 7

        repository.bookmarkArticle(articleId)

        verify(localDataSource).bookmarkArticle(articleId)
    }

    @Test
    fun `checkIsArticleBookmarked should return true when localDataSource returns true`() = runTest {
        val articleId = 42
        whenever(localDataSource.checkIsArticleBookmarked(articleId)).thenReturn(true)

        val result = repository.checkIsArticleBookmarked(articleId)

        assertTrue(result)
        verify(localDataSource).checkIsArticleBookmarked(articleId)
    }

    @Test
    fun `checkIsArticleBookmarked should return false when localDataSource returns false`() = runTest {
        val articleId = 999
        whenever(localDataSource.checkIsArticleBookmarked(articleId)).thenReturn(false)

        val result = repository.checkIsArticleBookmarked(articleId)

        assertFalse(result)
        verify(localDataSource).checkIsArticleBookmarked(articleId)
    }
}