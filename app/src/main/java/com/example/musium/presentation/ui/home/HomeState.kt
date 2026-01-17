package com.example.musium.presentation.ui.home

import com.example.musium.domain.model.Playlist
import com.example.musium.domain.model.User

data class HomeState(
    var isLoading: Boolean = false,
    var currentUser: User? = null,
    var errorMessage: String? = null,
    var topPlayList: List<Playlist> = emptyList()
)