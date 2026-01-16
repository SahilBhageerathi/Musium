package com.example.musium.presentation.ui.auth

sealed class AuthState {
    data object InitialState : AuthState()
    data object LoadingState : AuthState()
    data object ErrorState : AuthState()
}