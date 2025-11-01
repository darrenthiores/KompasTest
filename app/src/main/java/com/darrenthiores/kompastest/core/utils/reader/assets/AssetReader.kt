package com.darrenthiores.kompastest.core.utils.reader.assets

interface AssetReader {
    fun open(path: String): String
}