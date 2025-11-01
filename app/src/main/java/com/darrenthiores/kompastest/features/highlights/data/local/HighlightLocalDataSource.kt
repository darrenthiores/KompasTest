package com.darrenthiores.kompastest.features.highlights.data.local

import com.darrenthiores.kompastest.core.utils.parser.LocalJsonParser
import com.darrenthiores.kompastest.features.highlights.data.dto.BreakingNewsDto
import com.darrenthiores.kompastest.features.highlights.data.dto.HotTopicsDto
import com.darrenthiores.kompastest.features.highlights.data.dto.LiveReportDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HighlightLocalDataSource @Inject constructor(
    private val parser: LocalJsonParser
) {
    fun getBreakingNews(): Result<BreakingNewsDto> {
        return parser.loadJson<BreakingNewsDto>("files/breaking_news.json")
    }

    fun getLiveReport(): Result<LiveReportDto> {
        return parser.loadJson<LiveReportDto>("files/live_report.json")
    }

    fun getHotTopics(): Result<HotTopicsDto> {
        return parser.loadJson<HotTopicsDto>("files/hot_topics.json")
    }

}