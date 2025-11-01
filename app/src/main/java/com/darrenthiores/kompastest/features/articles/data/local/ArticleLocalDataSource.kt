package com.darrenthiores.kompastest.features.articles.data.local

import com.darrenthiores.kompastest.core.utils.parser.LocalJsonParser
import com.darrenthiores.kompastest.features.articles.data.dto.ArticleDetailDto
import com.darrenthiores.kompastest.features.articles.data.dto.ArticleDto
import com.darrenthiores.kompastest.features.articles.data.dto.ArticlesDto
import com.darrenthiores.kompastest.features.articles.data.dto.ArticlesPagingDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleLocalDataSource @Inject constructor(
    private val parser: LocalJsonParser
) {
    fun getAllArticles(): Result<List<ArticleDto>> {
        return parser.loadJson<ArticlesDto>(
            path = "files/articles.json"
        ).fold(
            onSuccess = { data ->
                Result.success(
                    data.articles.orEmpty()
                )
            },
            onFailure = { e ->
                Result.failure(e)
            }
        )
    }

    fun getNextArticles(
        page: Int
    ): Result<List<ArticleDto>> {
        return parser.loadJson<ArticlesPagingDto>(
            path = "files/articles_page.json"
        ).fold(
            onSuccess = { data ->
                data.articles?.firstOrNull { articlePage ->
                    articlePage.page == page
                }?.data?.let {
                    Result.success(it)
                } ?: Result.failure(
                    exception = Exception("Page not found")
                )
            },
            onFailure = { e ->
                Result.failure(e)
            }
        )
    }

    fun getArticleById(id: Int): Result<ArticleDetailDto> {
        return parser.loadJson<ArticleDetailDto>(
            path = "files/article_detail.json"
        )
    }
}