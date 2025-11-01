package com.darrenthiores.kompastest.features.articles.data.repository

import com.darrenthiores.kompastest.core.models.articles.Article
import com.darrenthiores.kompastest.core.utils.ext.mapToResource
import com.darrenthiores.kompastest.core.utils.models.Resource
import com.darrenthiores.kompastest.features.articles.data.local.ArticleLocalDataSource
import com.darrenthiores.kompastest.features.articles.domain.models.ArticleDetail
import com.darrenthiores.kompastest.features.articles.domain.repository.ArticleRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleRepositoryImpl @Inject constructor(
    private val localDataSource: ArticleLocalDataSource
): ArticleRepository {
    override suspend fun getAllArticles(): Resource<List<Article>> {
        val result = localDataSource
            .getAllArticles()

        return result
            .mapToResource { articles ->
                articles.mapNotNull { article ->
                    article.toDomain()
                }
            }
    }

    override suspend fun getNextArticles(page: Int): Resource<List<Article>> {
        val result = localDataSource
            .getNextArticles(
                page = page
            )

        return result
            .mapToResource { articles ->
                articles.mapNotNull { article ->
                    article.toDomain()
                }
            }
    }

    override suspend fun getArticleById(id: Int): Resource<ArticleDetail> {
        return localDataSource
            .getArticleById(
                id = id
            )
            .mapToResource { article ->
                article.toDomain()
            }
    }
}