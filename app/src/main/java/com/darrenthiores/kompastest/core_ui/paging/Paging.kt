package com.darrenthiores.kompastest.core_ui.paging

interface Paging {
    suspend fun loadNextItems()
    fun reset()
}