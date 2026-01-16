package com.example.musium.presentation.ui.auth

sealed interface AuthEvent {
    data object OnClickLoginBtnEvent : AuthEvent
}