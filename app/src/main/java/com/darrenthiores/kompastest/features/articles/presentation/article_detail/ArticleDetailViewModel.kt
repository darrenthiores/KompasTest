package com.darrenthiores.kompastest.features.articles.presentation.article_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darrenthiores.kompastest.core.utils.models.Resource
import com.darrenthiores.kompastest.core_ui.audio.AudioPlayer
import com.darrenthiores.kompastest.core_ui.utils.decodeFromRoute
import com.darrenthiores.kompastest.features.articles.domain.use_cases.GetArticleById
import com.darrenthiores.kompastest.features.bookmark.domain.use_cases.BookmarkArticle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getArticleById: GetArticleById,
    private val bookmarkArticle: BookmarkArticle,
    private val audioPlayer: AudioPlayer
): ViewModel() {
    private val _state = MutableStateFlow(ArticleDetailState())
    val state = _state.asStateFlow()

    private var id: Int? = null

    init {
        val title = savedStateHandle
            .get<String>("title")
            ?.decodeFromRoute()
        val articleId = savedStateHandle.get<Int>("article_id")

        id = articleId

        _state.update {
            it.copy(
                title = title
            )
        }

        id?.let {
            viewModelScope.launch {
                fetchArticleDetail(id = it)
            }
        }

        viewModelScope.launch {
            audioPlayer.isPlaying.collect { playing ->
                _state.update {
                    it.copy(
                        audioIsPlaying = playing
                    )
                }
            }
        }
        viewModelScope.launch {
            audioPlayer.isLoading.collect { isLoading ->
                _state.update {
                    it.copy(
                        audioIsLoading = isLoading
                    )
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        audioPlayer.release()
    }

    fun onEvent(event: ArticleDetailEvent) {
        when (event) {
            ArticleDetailEvent.OnErrorTryAgain -> {
                onRefresh()
            }
            is ArticleDetailEvent.Bookmark -> {
                onBookmarkArticle()
            }
            ArticleDetailEvent.ToggleBottomSheet -> {
                _state.update {
                    it.copy(
                        shareBottomSheetOpen = !it.shareBottomSheetOpen
                    )
                }
            }
            ArticleDetailEvent.ToggleAudioPlayer -> {
                val audioUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"

                if (state.value.audioIsPlaying) {
                    audioPlayer.pause()
                }
                else {
                    audioPlayer.play(
                        url = audioUrl
                    )
                }
            }
        }
    }

    private suspend fun fetchArticleDetail(id: Int) {
        _state.update {
            it.copy(
                articleState = it.articleState.copy(
                    isLoading = true
                )
            )
        }

        val result = getArticleById(
            id = id
        )

        when (result) {
            is Resource.Success -> {
                val data = result.data

                _state.update {
                    it.copy(
                        articleState = it.articleState.copy(
                            data = data
                        )
                    )
                }
            }
            is Resource.Error -> {
                _state.update {
                    it.copy(
                        articleState = it.articleState.copy(
                            error = result.error
                        )
                    )
                }
            }
            else -> {  }
        }

        _state.update {
            it.copy(
                articleState = it.articleState.copy(
                    isLoading = false
                )
            )
        }
    }

    private fun onBookmarkArticle() {
        viewModelScope.launch {
            state.value.articleState.data?.let { articleDetail ->
                articleDetail.id?.let { id ->
                    _state.update {
                        it.copy(
                            articleState = it.articleState.copy(
                                data = articleDetail.copy(
                                    bookmarked = !articleDetail.bookmarked
                                )
                            )
                        )
                    }

                    bookmarkArticle(id = id)
                }
            }
        }
    }

    private fun onRefresh() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    articleState = it.articleState.copy(
                        isLoading = true,
                        error = null
                    )
                )
            }

            // Simulate delay
            delay(1000L)

            id?.let { fetchArticleDetail(id = it) }
        }
    }
}