package com.example.musium.data.remote.auth

import android.util.Base64
import java.security.MessageDigest
import java.security.SecureRandom
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PkceManager @Inject constructor() {

    private var codeVerifier: String? = null

    fun generateCodeVerifier(): String {
        val secureRandom = SecureRandom()
        val code = ByteArray(32)
        secureRandom.nextBytes(code)

        codeVerifier = Base64.encodeToString(
            code,
            Base64.URL_SAFE or Base64.NO_WRAP or Base64.NO_PADDING
        )

        return codeVerifier!!
    }

    fun generateCodeChallenge(verifier: String): String {
        val bytes = verifier.toByteArray(Charsets.US_ASCII)
        val messageDigest = MessageDigest.getInstance("SHA-256")
        messageDigest.update(bytes)
        val digest = messageDigest.digest()

        return Base64.encodeToString(
            digest,
            Base64.URL_SAFE or Base64.NO_WRAP or Base64.NO_PADDING
        )
    }

    fun getVerifier(): String =
        codeVerifier ?: throw IllegalStateException("Code verifier not generated")
}
