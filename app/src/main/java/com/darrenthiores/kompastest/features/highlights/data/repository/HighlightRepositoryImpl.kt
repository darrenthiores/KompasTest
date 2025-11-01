package com.darrenthiores.kompastest.features.highlights.data.repository

import com.darrenthiores.kompastest.core.models.highlights.BreakingNews
import com.darrenthiores.kompastest.core.models.highlights.HotTopics
import com.darrenthiores.kompastest.core.models.highlights.LiveReport
import com.darrenthiores.kompastest.core.utils.ext.mapToResource
import com.darrenthiores.kompastest.core.utils.models.Resource
import com.darrenthiores.kompastest.features.highlights.data.local.HighlightLocalDataSource
import com.darrenthiores.kompastest.features.highlights.domain.repository.HighlightRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HighlightRepositoryImpl @Inject constructor(
    private val localDataSource: HighlightLocalDataSource,
): HighlightRepository {
    override fun getBreakingNews(): Resource<BreakingNews> {
        return localDataSource.getBreakingNews()
            .mapToResource {
                it.toDomain()
            }
    }

    override fun getLiveReport(): Resource<LiveReport> {
        return localDataSource.getLiveReport()
            .mapToResource {
                it.toDomain()
            }
    }

    override fun getHotTopics(): Resource<HotTopics> {
        return localDataSource.getHotTopics()
            .mapToResource {
                it.toDomain()
            }
    }
}