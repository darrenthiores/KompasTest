package com.darrenthiores.kompastest.core.utils.parser

import com.darrenthiores.kompastest.core.utils.reader.assets.AssetReader
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalJsonParser @Inject constructor(
    private val reader: AssetReader
) {
    @OptIn(ExperimentalSerializationApi::class)
    @PublishedApi
    internal val jsonLenient = Json {
        ignoreUnknownKeys = true
        isLenient = true
        allowTrailingComma = true
    }

    @PublishedApi
    internal fun jsonString(path: String): String = reader.open(path)

    inline fun <reified T> loadJson(path: String): Result<T> {
        return runCatching {
            val json = jsonString(path = path)
            jsonLenient.decodeFromString<T>(json)
        }
    }
}