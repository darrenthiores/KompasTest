package com.darrenthiores.kompastest.core.models.highlights

import com.darrenthiores.kompastest.core.models.articles.Article

data class LiveReport(
    val reportType: String,
    val mainArticle: Article?,
    val relatedArticles: List<Article>,
    val moreReports: MoreReports?,
    val featuredArticles: List<Article>
)