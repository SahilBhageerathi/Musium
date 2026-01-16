package com.example.musium.presentation.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.example.musium.MainActivity
import com.example.musium.data.remote.auth.AppConfig
import com.example.musium.data.remote.auth.AuthService
import com.example.musium.data.remote.auth.AuthTokenStore
import com.example.musium.data.remote.auth.PkceManager
import dagger.hilt.android.AndroidEntryPoint
import jakarta.inject.Inject
import kotlinx.coroutines.launch
import com.example.musium.data.remote.auth.SpotifyAuthConstants.REDIRECT_URI

@AndroidEntryPoint
class AuthCallbackActivity : ComponentActivity() {

    @Inject
    lateinit var authApi: AuthService
    @Inject lateinit var tokenStore: AuthTokenStore
    @Inject lateinit var pkceManager: PkceManager
    @Inject lateinit var appConfig: AppConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        handleRedirect(intent)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleRedirect(intent)
    }

    private fun handleRedirect(intent: Intent) {
        val uri = intent.data ?: return finish()

        val code = uri.getQueryParameter("code")
        val error = uri.getQueryParameter("error")

        if (error != null || code == null) {
            finish()
            return
        }

        lifecycleScope.launch {
            exchangeCodeForToken(code)
            goBackToApp()
        }
    }

    private suspend fun exchangeCodeForToken(code: String) {
        val codeVerifier = pkceManager.getVerifier()
            ?: return

        val response = authApi.exchangeCode(
            grantType = "authorization_code",
            code = code,
            redirectUri = REDIRECT_URI,
            clientId = appConfig.clientId,
            codeVerifier = codeVerifier
        )

        tokenStore.saveTokens(
            access = response.accessToken,
            refresh = response.refreshToken!!
        )
        tokenStore.setLoggedIn(true)
    }

    private fun goToHome() {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                    Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
        finish()
    }


    private fun goBackToApp() {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        }
        startActivity(intent)
        finish()
    }


}
