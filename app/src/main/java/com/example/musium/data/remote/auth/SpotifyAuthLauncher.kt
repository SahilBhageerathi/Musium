package com.example.musium.data.remote.auth

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.browser.customtabs.CustomTabsIntent
import com.example.musium.data.remote.auth.SpotifyAuthConstants.REDIRECT_URI
import jakarta.inject.Inject

class SpotifyAuthLauncher @Inject constructor(
    private val appConfig: AppConfig,
    private val pkceManager: PkceManager
) {

    fun launchAuth(context: Context) {
        val codeVerifier = pkceManager.generateCodeVerifier()
        val codeChallenge = pkceManager.generateCodeChallenge(codeVerifier)

        val uri = Uri.Builder()
            .scheme("https")
            .authority("accounts.spotify.com")
            .appendPath("authorize")
            .appendQueryParameter("client_id", appConfig.clientId)
            .appendQueryParameter("response_type", "code")
            .appendQueryParameter("redirect_uri", REDIRECT_URI)
            .appendQueryParameter("code_challenge_method", "S256")
            .appendQueryParameter("code_challenge", codeChallenge)
            .appendQueryParameter(
                "scope",
                "user-read-email user-read-private"
            )
            .build()

        Log.d("SPOTIFY_AUTH", "REDIRECT_URI = $REDIRECT_URI")

        Log.d("SPOTIFY_AUTH", "AUTH URL = $uri")


        CustomTabsIntent.Builder()
            .build()
            .launchUrl(context, uri)
    }
}
