package com.darrenthiores.kompastest.features.highlights.domain.use_cases

import com.darrenthiores.kompastest.core.models.highlights.BreakingNews
import com.darrenthiores.kompastest.core.utils.models.Resource
import com.darrenthiores.kompastest.features.bookmark.domain.repository.BookmarkRepository
import com.darrenthiores.kompastest.features.highlights.domain.repository.HighlightRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetBreakingNews @Inject constructor(
    private val highlightRepository: HighlightRepository,
    private val bookmarkRepository: BookmarkRepository
) {
    suspend operator fun invoke(): Resource<BreakingNews> {
        val breakingNews = highlightRepository.getBreakingNews()
        val bookmarkedIds = bookmarkRepository.getAllBookmarkedIds()

        return when (breakingNews) {
            is Resource.Success -> {
                breakingNews.data?.let { breakingNews ->
                    Resource.Success(
                        data = breakingNews.copy(
                            articles = breakingNews.articles.map { article ->
                                article.copy(
                                    bookmarked = bookmarkedIds.contains(
                                        article.id
                                    )
                                )
                            }
                        )
                    )
                } ?: breakingNews
            }
            else -> breakingNews
        }
    }
}