package com.darrenthiores.kompastest.features.homepage.presentation.models

enum class HomeCategory {
    CHOICE,
    LATEST,
    MAIN,
    FAVORITE,
    POPULAR;

    fun displayText(): String {
        return when(this) {
            CHOICE -> "PILIHANKU"
            LATEST -> "TERBARU"
            MAIN -> "BERITA UTAMA"
            FAVORITE -> "FAVORIT PEMBACA"
            POPULAR -> "POPULER"
        }
    }
}