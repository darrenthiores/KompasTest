package com.darrenthiores.kompastest.features.stories.data.local.source

import com.darrenthiores.kompastest.core.utils.parser.LocalJsonParser
import com.darrenthiores.kompastest.features.stories.data.dto.StoriesDto
import com.darrenthiores.kompastest.features.stories.data.dto.StoryDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StoryLocalDataSource @Inject constructor(
    private val parser: LocalJsonParser
) {
    fun getAllStories(): Result<List<StoryDto>> {
        return parser.loadJson<StoriesDto>("files/stories.json")
            .fold(
                onSuccess = {
                    Result.success(it.stories.orEmpty())
                },
                onFailure = {
                    Result.failure(it)
                }
            )
    }
}