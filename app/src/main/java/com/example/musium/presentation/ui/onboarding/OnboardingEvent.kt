package com.example.musium.presentation.ui.onboarding

sealed interface OnboardingEvent {

    data object InitializeEvent:OnboardingEvent

}