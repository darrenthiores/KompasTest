package com.darrenthiores.kompastest.features.bookmark.domain.use_cases

import com.darrenthiores.kompastest.features.bookmark.domain.repository.BookmarkRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class BookmarkArticle @Inject constructor(
    private val repository: BookmarkRepository
) {
    suspend operator fun invoke(id: Int) {
        repository.bookmarkArticle(
            id = id
        )
    }
}