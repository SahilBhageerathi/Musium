package com.example.musium.data.remote.auth

import jakarta.inject.Inject
import com.example.musium.BuildConfig
interface AppConfig {
    val clientId: String
}

class AppConfigImpl @Inject constructor() : AppConfig {
    override val clientId: String
        get() = BuildConfig.SPOTIFY_CLIENT_ID
}