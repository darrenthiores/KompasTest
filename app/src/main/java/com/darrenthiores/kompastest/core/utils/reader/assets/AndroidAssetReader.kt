package com.darrenthiores.kompastest.core.utils.reader.assets

import android.content.res.AssetManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AndroidAssetReader @Inject constructor(
    private val assetManager: AssetManager
) : AssetReader {
    override fun open(path: String): String {
        return assetManager.open(path).bufferedReader().use { it.readText() }
    }
}