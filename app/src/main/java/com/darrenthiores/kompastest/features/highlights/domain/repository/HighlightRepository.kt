package com.darrenthiores.kompastest.features.highlights.domain.repository

import com.darrenthiores.kompastest.core.models.highlights.BreakingNews
import com.darrenthiores.kompastest.core.models.highlights.HotTopics
import com.darrenthiores.kompastest.core.models.highlights.LiveReport
import com.darrenthiores.kompastest.core.utils.models.Resource

interface HighlightRepository {
    fun getBreakingNews(): Resource<BreakingNews>
    fun getLiveReport(): Resource<LiveReport>
    fun getHotTopics(): Resource<HotTopics>
}