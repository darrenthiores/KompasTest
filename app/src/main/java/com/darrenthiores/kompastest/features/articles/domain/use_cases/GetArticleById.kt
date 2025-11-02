package com.darrenthiores.kompastest.features.articles.domain.use_cases

import com.darrenthiores.kompastest.core.utils.models.Resource
import com.darrenthiores.kompastest.features.articles.domain.models.ArticleDetail
import com.darrenthiores.kompastest.features.articles.domain.repository.ArticleRepository
import com.darrenthiores.kompastest.features.bookmark.domain.repository.BookmarkRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetArticleById @Inject constructor(
    private val articleRepository: ArticleRepository,
    private val bookmarkRepository: BookmarkRepository
) {
    suspend operator fun invoke(
        id: Int
    ): Resource<ArticleDetail> {
        val articles = articleRepository.getArticleById(
            id = id
        )

        return when (articles) {
            is Resource.Success -> {
                articles.data?.let { article ->
                    Resource.Success(
                        data = article.copy(
                            bookmarked = article.id?.let { id ->
                                bookmarkRepository
                                    .checkIsArticleBookmarked(
                                        id = id
                                    )
                            } ?: false
                        )
                    )
                } ?: articles
            }
            else -> articles
        }
    }
}