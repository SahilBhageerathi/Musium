package com.example.musium.data.remote.auth

import jakarta.inject.Inject
import kotlinx.coroutines.runBlocking
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator @Inject constructor(
    private val authApi: AuthService,
    private val tokenStore: AuthTokenStore,
    private val appConfig: AppConfig
) : okhttp3.Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val refreshToken = runBlocking {
            tokenStore.getRefreshToken()
        } ?: return null

        val newToken = runBlocking {
            authApi.refreshToken(
                grantType = "refresh_token",
                refreshToken = refreshToken,
                clientId = appConfig.clientId
            )
        }

        runBlocking {
            tokenStore.saveTokens(
                newToken.accessToken,
                refreshToken
            )
        }

        return response.request.newBuilder()
            .header("Authorization", "Bearer ${newToken.accessToken}")
            .build()
    }
}