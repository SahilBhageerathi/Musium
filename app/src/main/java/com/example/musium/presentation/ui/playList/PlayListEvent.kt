package com.example.musium.presentation.ui.playList

sealed interface PlayListEvent{
    data class InitializeEvent( val playListId:String) :PlayListEvent

}