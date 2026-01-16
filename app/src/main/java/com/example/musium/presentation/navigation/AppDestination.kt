package com.example.musium.presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface AppDestination : NavKey {
    @Serializable
    data object Home : AppDestination

    @Serializable
    data object Profile : AppDestination

    @Serializable
    data object Settings : AppDestination
    @Serializable
    data object Onboarding : AppDestination

    @Serializable
    data object SignInScreen : AppDestination

    @Serializable
    data object LoginScreen : AppDestination

    @Serializable
    data class PlayListScreen(
        val playlistId: String,
        val playListImage: String?,
        val playListName: String,
        val playListDescription: String?
    ) : AppDestination
}