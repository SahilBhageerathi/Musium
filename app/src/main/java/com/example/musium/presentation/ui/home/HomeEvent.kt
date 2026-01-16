package com.example.musium.presentation.ui.home
sealed interface  HomeEvent {
//    data class ShowErrorEvent(val message:String) : HomeEvent
    data object InitializeEvent : HomeEvent
}