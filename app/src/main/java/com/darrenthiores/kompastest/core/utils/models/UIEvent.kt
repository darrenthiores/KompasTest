package com.darrenthiores.kompastest.core.utils.models

sealed interface UIEvent {
    data object Success : UIEvent
    data class Navigate(
        val route: String
    ) : UIEvent
    data class Error(
        val message: String?
    ): UIEvent
    data class ShowSnackBar(
        val message: String?
    ): UIEvent
}