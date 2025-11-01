package com.darrenthiores.kompastest.features.homepage.domain.use_cases

import com.darrenthiores.kompastest.core.utils.models.Resource
import com.darrenthiores.kompastest.features.articles.domain.use_cases.GetNextArticles
import com.darrenthiores.kompastest.features.homepage.domain.models.HomepageBlock
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetNextHomepageBlocks @Inject constructor(
    private val getNextArticles: GetNextArticles
) {
    suspend operator fun invoke(page: Int): Resource<List<HomepageBlock>> {
        val nextArticles = getNextArticles(page = page)
        val notFoundError = Resource.Error<List<HomepageBlock>>(
            error = "End of blocks"
        )

        return when (nextArticles) {
            is Resource.Success -> {
                nextArticles.data?.let { articles ->
                    Resource.Success(
                        data = listOf(
                            HomepageBlock.ArticlesBlock(
                                articles = articles
                            )
                        )
                    )
                } ?: notFoundError
            }
            else -> notFoundError
        }
    }
}