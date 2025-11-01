package com.darrenthiores.kompastest.app.navigation.routes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Extension
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.ui.graphics.vector.ImageVector

enum class TopLevelDestination(
    val title: String,
    val icon: ImageVector
) {
    HOME(
        title = "Beranda",
        icon = Icons.Default.Home
    ),
    PAPER(
        title = "ePaper",
        icon = Icons.Default.Newspaper
    ),
    PUZZLE(
        title = "TTS",
        icon = Icons.Default.Extension
    ),
    BOOK(
        title = "Buku",
        icon = Icons.Default.Book
    ),
    ACCOUNT(
        title = "Akun",
        icon = Icons.Default.AccountCircle
    );

    companion object{
        fun fromRoute(route: String?): TopLevelDestination? =
            when (route?.substringBefore("/")) {
                HOME.name -> HOME
                PAPER.name -> PAPER
                PUZZLE.name -> PUZZLE
                BOOK.name -> BOOK
                ACCOUNT.name -> ACCOUNT
                else -> null
            }
    }
}