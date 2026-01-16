package com.example.musium.presentation.ui.onboarding

import androidx.lifecycle.ViewModel

import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class OnboardingViewModel @Inject constructor(

) : ViewModel() {

    private val _state = MutableStateFlow<OnboardingState>(OnboardingState.Normal)
    val state: StateFlow<OnboardingState> = _state

    private val _event = MutableSharedFlow<OnboardingEvent>()
    val event: SharedFlow<OnboardingEvent> = _event
    //    val event = _event.asSharedFlow()  // can also be written like this


}