package com.darrenthiores.kompastest.features.stories.domain.use_cases

import com.darrenthiores.kompastest.core.models.stories.Story
import com.darrenthiores.kompastest.core.utils.models.Resource
import com.darrenthiores.kompastest.features.bookmark.domain.repository.BookmarkRepository
import com.darrenthiores.kompastest.features.stories.domain.repository.StoryRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetAllStories @Inject constructor(
    private val storyRepository: StoryRepository,
    private val bookmarkRepository: BookmarkRepository
) {
    suspend operator fun invoke(): Resource<List<Story>> {
        val stories = storyRepository.getAllStories()
        val bookmarkedIds = bookmarkRepository.getAllBookmarkedIds().toSet()

        return when (stories) {
            is Resource.Success -> {
                stories.data?.let { stories ->
                    Resource.Success(
                        data = stories.map { story ->
                            story.copy(
                                articles = story.articles?.map { article ->
                                    article.copy(
                                        bookmarked = bookmarkedIds
                                            .contains(article.id)
                                    )
                                }
                            )
                        }
                    )
                } ?: stories
            }
            else -> stories
        }
    }
}