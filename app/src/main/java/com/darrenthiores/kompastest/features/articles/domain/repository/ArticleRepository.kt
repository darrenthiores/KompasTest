package com.darrenthiores.kompastest.features.articles.domain.repository

import com.darrenthiores.kompastest.core.models.articles.Article
import com.darrenthiores.kompastest.core.utils.models.Resource
import com.darrenthiores.kompastest.features.articles.domain.models.ArticleDetail

interface ArticleRepository {
    suspend fun getAllArticles(): Resource<List<Article>>
    suspend fun getNextArticles(page: Int): Resource<List<Article>>
    suspend fun getArticleById(id: Int): Resource<ArticleDetail>
}