package com.darrenthiores.kompastest.features.articles.domain.use_cases

import com.darrenthiores.kompastest.core.models.articles.Article
import com.darrenthiores.kompastest.core.utils.models.Resource
import com.darrenthiores.kompastest.features.articles.domain.repository.ArticleRepository
import com.darrenthiores.kompastest.features.bookmark.domain.repository.BookmarkRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetNextArticles @Inject constructor(
    private val articleRepository: ArticleRepository,
    private val bookmarkRepository: BookmarkRepository
) {
    suspend operator fun invoke(
        page: Int
    ): Resource<List<Article>> {
        val articles = articleRepository.getNextArticles(
            page = page
        )
        val bookmarkedIds = bookmarkRepository.getAllBookmarkedIds().toSet()

        return when (articles) {
            is Resource.Success -> {
                articles.data?.let { articles ->
                    Resource.Success(
                        data = articles.map { article ->
                            article.copy(
                                bookmarked = bookmarkedIds
                                    .contains(article.id)
                            )
                        }
                    )
                } ?: articles
            }
            else -> articles
        }
    }
}