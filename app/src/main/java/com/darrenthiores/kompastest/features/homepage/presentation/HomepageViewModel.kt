package com.darrenthiores.kompastest.features.homepage.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darrenthiores.kompastest.core.utils.models.Resource
import com.darrenthiores.kompastest.core_ui.paging.AppPagingFactory
import com.darrenthiores.kompastest.features.homepage.domain.models.HomepageBlock
import com.darrenthiores.kompastest.features.homepage.domain.use_cases.GetHomepageBlocks
import com.darrenthiores.kompastest.features.homepage.domain.use_cases.GetNextHomepageBlocks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomepageViewModel @Inject constructor(
    private val getHomepageBlocks: GetHomepageBlocks,
    private val getNextHomepageBlocks: GetNextHomepageBlocks
): ViewModel() {
    private val _state = MutableStateFlow(HomepageState())
    val state = _state.asStateFlow()

    private var homePaging: AppPagingFactory<Int, HomepageBlock>? = null

    init {
        homePaging = AppPagingFactory(
            initialKey = 1,
            onLoading = { isLoading ->
                _state.update {
                    it.copy(
                        homePagingState = it.homePagingState.copy(
                            isLoading = isLoading,
                        )
                    )
                }
            },
            onRequest = { nextKey ->
                getNextHomepageBlocks(
                    page = nextKey
                )
            },
            getNextKey = { _ ->
                (state.value.homePagingState.key ?: 1) + 1
            },
            onError = { error ->
                _state.update {
                    it.copy(
                        homePagingState = it.homePagingState.copy(
                            error = error,
                            endReached = true
                        )
                    )
                }
            },
            onSuccess = { items, key ->
                _state.update {
                    it.copy(
                        homePagingState = it.homePagingState.copy(
                            items = it.homePagingState.items + items,
                            key = key,
                            endReached = items.isEmpty()
                        )
                    )
                }
            }
        )

        viewModelScope.launch {
            fetchBlocks()
        }
    }

    fun onEvent(event: HomepageEvent) {
        when (event) {
            HomepageEvent.OnEndOfListReached -> loadNextItem()
            HomepageEvent.OnErrorTryAgain -> onRefresh()
            HomepageEvent.OnPullToRefresh -> {
                _state.update {
                    it.copy(
                        isRefreshing = true
                    )
                }

                onRefresh()
            }
        }
    }

    private suspend fun fetchBlocks() {
        _state.update {
            it.copy(
                homePagingState = it.homePagingState.copy(
                    isLoading = true
                )
            )
        }

        val result = getHomepageBlocks()

        when (result) {
            is Resource.Success -> {
                val data = result.data

                _state.update {
                    it.copy(
                        homePagingState = it.homePagingState.copy(
                            items = data.orEmpty()
                        )
                    )
                }
            }
            is Resource.Error -> {
                _state.update {
                    it.copy(
                        homePagingState = it.homePagingState.copy(
                            error = result.error
                        )
                    )
                }
            }
            else -> {  }
        }

        _state.update {
            it.copy(
                homePagingState = it.homePagingState.copy(
                    isLoading = false
                )
            )
        }
    }

    private fun loadNextItem() {
        viewModelScope.launch {
            val pagingState = state.value.homePagingState

            if(
                pagingState.endReached &&
                pagingState.isLoading
            ) { return@launch }

            _state.update {
                it.copy(
                    homePagingState = it.homePagingState.copy(
                        isLoading = true
                    )
                )
            }

            // Simulate delay
            delay(1000L)

            homePaging?.loadNextItems()
        }
    }

    private fun onRefresh() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    homePagingState = it.homePagingState.copy(
                        isLoading = true,
                        items = emptyList(),
                        error = null,
                        endReached = false,
                        key = null
                    )
                )
            }
            homePaging?.reset()

            // Simulate delay
            delay(1000L)

            fetchBlocks()

            _state.update {
                it.copy(
                    isRefreshing = false
                )
            }
        }
    }
}