package com.example.musium.presentation.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musium.data.remote.auth.AuthTokenStore
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class AuthViewModel @Inject constructor(
    tokenStore: AuthTokenStore
): ViewModel(){

    private val _authState = MutableStateFlow<AuthState>(AuthState.InitialState)
    val authState : StateFlow<AuthState> =_authState

    private val _authEvent = MutableSharedFlow<AuthEvent>()
    val authEvent : SharedFlow<AuthEvent> =_authEvent



    fun onSpotifyLoginClick() {
        viewModelScope.launch {
            _authEvent.emit(AuthEvent.OnClickLoginBtnEvent)
        }
    }


    val isLoggedIn = tokenStore.isLoggedInFlow.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        false
    )

}