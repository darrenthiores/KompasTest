package com.darrenthiores.kompastest.core_ui.utils

import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@OptIn(ExperimentalEncodingApi::class)
fun String.encodeForRoute(): String {
    if (this.isBlank()) return ""
    return Base64.UrlSafe.encode(this.encodeToByteArray())
}

@OptIn(ExperimentalEncodingApi::class)
fun String.decodeFromRoute(): String {
    if (this.isBlank()) return ""
    return Base64.UrlSafe.decode(this).decodeToString()
}