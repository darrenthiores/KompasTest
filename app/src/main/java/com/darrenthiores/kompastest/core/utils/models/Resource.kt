package com.darrenthiores.kompastest.core.utils.models

sealed class Resource<out T>(
    val data: T? = null,
    val error: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(error: String, data: T? = null) : Resource<T>(data, error)
    data object Loading: Resource<Nothing>()
}