package com.darrenthiores.kompastest.core_ui.paging

data class PagingState<Key, Item>(
    val isLoading: Boolean = false,
    val items: List<Item> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val key: Key? = null
)