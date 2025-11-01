package com.darrenthiores.kompastest.features.highlights.data.dto


import com.darrenthiores.kompastest.core.models.articles.Article
import com.darrenthiores.kompastest.core.models.highlights.LiveReport
import com.darrenthiores.kompastest.core.models.highlights.MoreReports
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LiveReportDto(
    @SerialName("report_type") val reportType: String? = null,
    @SerialName("main_article") val mainArticle: MainArticleDto? = null,
    @SerialName("related_articles") val relatedArticles: List<RelatedArticleDto>? = null,
    @SerialName("more_reports") val moreReports: MoreReportsDto? = null,
    @SerialName("featured_articles") val featuredArticles: List<FeaturedArticleDto>? = null
) {
    fun toDomain(): LiveReport {
        return LiveReport(
            reportType = reportType.orEmpty(),
            mainArticle = mainArticle?.toDomain(),
            relatedArticles = relatedArticles?.map { it.toDomain() }.orEmpty(),
            moreReports = moreReports?.toDomain(),
            featuredArticles = featuredArticles?.map { it.toDomain() }.orEmpty()
        )
    }
    
    @Serializable
    data class MainArticleDto(
        val id: Int? = null,
        val category: String? = null,
        val title: String? = null,
        @SerialName("image_description") val imageDescription: String? = null,
        @SerialName("image_url") val imageUrl: String? = null,
        @SerialName("published_time") val publishedTime: String? = null
    ) {
        fun toDomain(): Article {
            return Article(
                id = id ?: -1,
                title = title.orEmpty(),
                description = "",
                imageUrl = imageUrl,
                imageDescription = imageDescription,
                category = category,
                publishedTime = publishedTime
            )
        }
    }

    @Serializable
    data class RelatedArticleDto(
        val id: Int? = null,
        val title: String? = null,
        @SerialName("published_time") val publishedTime: String? = null
    ) {
        fun toDomain(): Article {
            return Article(
                id = id ?: -1,
                title = title.orEmpty(),
                description = "",
                imageUrl = null,
                publishedTime = publishedTime
            )
        }
    }

    @Serializable
    data class MoreReportsDto(
        val label: String? = null,
        val count: String? = null
    ) {
        fun toDomain(): MoreReports {
            return MoreReports(
                label = label.orEmpty(),
                count = count.orEmpty()
            )
        }
    }

    @Serializable
    data class FeaturedArticleDto(
        val id: Int? = null,
        val title: String? = null,
        @SerialName("image_description") val imageDescription: String? = null,
        @SerialName("image_url") val imageUrl: String? = null
    ) {
        fun toDomain(): Article {
            return Article(
                id = id ?: -1,
                title = title.orEmpty(),
                description = "",
                imageUrl = imageUrl,
                imageDescription = imageDescription,
            )
        }
    }
}