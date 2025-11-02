package com.darrenthiores.kompastest.core.utils.models

data class DataState<Data, E>(
    val data: Data? = null,
    val error: E? = null,
    val isLoading: Boolean = false
)
