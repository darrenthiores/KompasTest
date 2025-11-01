package com.darrenthiores.kompastest.core_ui.paging

import com.darrenthiores.kompastest.core.utils.models.Resource

class AppPagingFactory<Key, Item>(
    private val initialKey: Key,
    private val onLoading: (Boolean) -> Unit,
    private val onRequest: suspend (nextKey: Key) -> Resource<List<Item>>,
    private val getNextKey: suspend (List<Item>) -> Key,
    private val onError: suspend (String?) -> Unit,
    private val onSuccess: suspend (items: List<Item>, newKey: Key) -> Unit
): Paging {
    private var currentKey = initialKey
    private var isMakingRequest = false

    override suspend fun loadNextItems() {
        if(isMakingRequest) return

        isMakingRequest = true
        onLoading(true)

        val result = onRequest(currentKey)

        isMakingRequest = false

        val items = when (result) {
            is Resource.Success -> result.data ?: emptyList()
            else -> {
                onError(result.error)
                onLoading(false)
                return
            }
        }

        currentKey = getNextKey(items)
        onSuccess(items, currentKey)
        onLoading(false)
    }

    override fun reset() {
        currentKey = initialKey
    }
}