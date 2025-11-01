package com.darrenthiores.kompastest.features.homepage.domain.models

import com.darrenthiores.kompastest.core.models.advertisement.Ads
import com.darrenthiores.kompastest.core.models.advertisement.Campaign
import com.darrenthiores.kompastest.core.models.articles.Article
import com.darrenthiores.kompastest.core.models.highlights.BreakingNews
import com.darrenthiores.kompastest.core.models.highlights.HotTopics
import com.darrenthiores.kompastest.core.models.highlights.LiveReport
import com.darrenthiores.kompastest.core.models.stories.Story

sealed class HomepageBlock(
    val id: Int
) {
    companion object {
        private var currentId = 0
        private fun nextId(): Int {
            currentId += 1
            return currentId
        }
    }

    data class BreakingNewsBlock(
        val breakingNews: BreakingNews
    ): HomepageBlock(id = nextId())
    data class LiveReportBlock(
        val liveReport: LiveReport
    ): HomepageBlock(id = nextId())
    data class CampaignBlock(
        val campaign: Campaign
    ): HomepageBlock(id = nextId())
    data class HotTopicsBlock(
        val hotTopics: HotTopics
    ): HomepageBlock(id = nextId())
    data class StoryBlock(
        val story: Story
    ): HomepageBlock(id = nextId())
    data class AdsBlock(
        val ads: Ads
    ): HomepageBlock(id = nextId())
    data class ArticlesBlock(
        val articles: List<Article>
    ): HomepageBlock(id = nextId())
}