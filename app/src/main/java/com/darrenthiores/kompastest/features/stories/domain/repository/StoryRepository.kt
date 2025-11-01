package com.darrenthiores.kompastest.features.stories.domain.repository

import com.darrenthiores.kompastest.core.models.stories.Story
import com.darrenthiores.kompastest.core.utils.models.Resource

interface StoryRepository {
    fun getAllStories(): Resource<List<Story>>
}