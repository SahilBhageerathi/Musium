package com.example.musium.data.remote.auth

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class AuthTokenStore @Inject constructor(
//    TODO:convert to encrypted data store

    private val dataStore: DataStore<Preferences>
) {
    companion object {
        val ACCESS_TOKEN = stringPreferencesKey("access_token")
        val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
        val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
    }

    suspend fun saveTokens(access: String, refresh: String) {
        dataStore.edit { prefs ->
            prefs[ACCESS_TOKEN] = access
            prefs[REFRESH_TOKEN] = refresh
        }
    }

    suspend fun getAccessToken(): String? =
        dataStore.data.first()[ACCESS_TOKEN]

    suspend fun getRefreshToken(): String? =
        dataStore.data.first()[REFRESH_TOKEN]


    suspend fun setLoggedIn(value: Boolean) {
        dataStore.edit { it[IS_LOGGED_IN] = value }
    }

    val isLoggedInFlow: Flow<Boolean> =
        dataStore.data.map { it[IS_LOGGED_IN] ?: false }
}
