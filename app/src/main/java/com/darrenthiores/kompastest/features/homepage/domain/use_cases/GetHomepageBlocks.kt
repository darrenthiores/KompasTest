package com.darrenthiores.kompastest.features.homepage.domain.use_cases

import com.darrenthiores.kompastest.core.utils.models.Resource
import com.darrenthiores.kompastest.core.utils.resources.Constants
import com.darrenthiores.kompastest.features.advertisement.domain.repository.AdvertisementRepository
import com.darrenthiores.kompastest.features.articles.domain.use_cases.GetAllArticles
import com.darrenthiores.kompastest.features.highlights.domain.repository.HighlightRepository
import com.darrenthiores.kompastest.features.highlights.domain.use_cases.GetBreakingNews
import com.darrenthiores.kompastest.features.homepage.domain.models.HomepageBlock
import com.darrenthiores.kompastest.features.stories.domain.use_cases.GetAllStories
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

@ViewModelScoped
class GetHomepageBlocks @Inject constructor(
    private val getAllArticles: GetAllArticles,
    private val getAllStories: GetAllStories,
    private val getBreakingNews: GetBreakingNews,
    private val advertisementRepository: AdvertisementRepository,
    private val highlightRepository: HighlightRepository
) {
    suspend operator fun invoke(): Resource<List<HomepageBlock>> = coroutineScope {
        val breakingNewsDeferred = async { getBreakingNews() }
        val storiesDeferred = async { getAllStories() }
        val articlesDeferred = async { getAllArticles() }

        val liveReportResult = highlightRepository.getLiveReport()
        val campaignResult = advertisementRepository.getCampaign()
        val hotTopicsResult = highlightRepository.getHotTopics()
        val adsResult = advertisementRepository.getAds()

        val breakingNewsResult = breakingNewsDeferred.await()
        val storiesResult = storiesDeferred.await()
        val articlesResult = articlesDeferred.await()

        val blocks = mutableListOf<HomepageBlock>()

        if (breakingNewsResult is Resource.Success && breakingNewsResult.data != null) {
            blocks.add(HomepageBlock.BreakingNewsBlock(breakingNewsResult.data))
        }

        if (liveReportResult is Resource.Success && liveReportResult.data != null) {
            blocks.add(HomepageBlock.LiveReportBlock(liveReportResult.data))
        }

        if (campaignResult is Resource.Success && campaignResult.data != null) {
            blocks.add(HomepageBlock.CampaignBlock(campaignResult.data))
        }

        if (hotTopicsResult is Resource.Success && hotTopicsResult.data != null) {
            blocks.add(HomepageBlock.HotTopicsBlock(hotTopicsResult.data))
        }

        if (storiesResult is Resource.Success && !storiesResult.data.isNullOrEmpty()) {
            storiesResult.data.forEach { story ->
                blocks.add(HomepageBlock.StoryBlock(story))
            }
        }

        if (adsResult is Resource.Success && adsResult.data != null) {
            blocks.add(HomepageBlock.AdsBlock(adsResult.data))
        }

        if (articlesResult is Resource.Success && !articlesResult.data.isNullOrEmpty()) {
            blocks.add(HomepageBlock.ArticlesBlock(articlesResult.data))
        }

        if (adsResult is Resource.Success && adsResult.data != null) {
            blocks.add(HomepageBlock.AdsBlock(adsResult.data))
        }

        if (blocks.isEmpty()) {
            Resource.Error(Constants.COMMON_ERROR_MESSAGE)
        } else {
            Resource.Success(blocks)
        }
    }
}