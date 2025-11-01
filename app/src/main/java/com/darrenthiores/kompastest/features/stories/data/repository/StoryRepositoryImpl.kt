package com.darrenthiores.kompastest.features.stories.data.repository

import com.darrenthiores.kompastest.core.models.stories.Story
import com.darrenthiores.kompastest.core.utils.ext.mapToResource
import com.darrenthiores.kompastest.core.utils.models.Resource
import com.darrenthiores.kompastest.features.stories.data.local.source.StoryLocalDataSource
import com.darrenthiores.kompastest.features.stories.domain.repository.StoryRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StoryRepositoryImpl @Inject constructor(
    private val storyLocalDataSource: StoryLocalDataSource
): StoryRepository {
    override fun getAllStories(): Resource<List<Story>> {
        return storyLocalDataSource.getAllStories()
            .mapToResource { stories ->
                stories.map { story ->
                    story.toDomain()
                }
            }
    }
}