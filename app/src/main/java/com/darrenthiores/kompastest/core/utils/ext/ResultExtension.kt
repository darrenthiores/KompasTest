package com.darrenthiores.kompastest.core.utils.ext

import com.darrenthiores.kompastest.core.utils.models.Resource
import com.darrenthiores.kompastest.core.utils.resources.Constants

inline fun <T, R> Result<T>.mapToResource(
    transform: (T) -> R
): Resource<R> = fold(
    onSuccess = { Resource.Success(transform(it)) },
    onFailure = { Resource.Error(it.message ?: Constants.COMMON_ERROR_MESSAGE) }
)