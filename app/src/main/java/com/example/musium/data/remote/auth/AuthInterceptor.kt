package com.example.musium.data.remote.auth

import jakarta.inject.Inject
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor @Inject constructor(
    private val tokenStore: AuthTokenStore
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking {
            tokenStore.getAccessToken()
        }

        val request = chain.request().newBuilder().apply {
            if (!token.isNullOrEmpty()) {
                addHeader("Authorization", "Bearer $token")
            }
        }.build()

        return chain.proceed(request)
    }
}