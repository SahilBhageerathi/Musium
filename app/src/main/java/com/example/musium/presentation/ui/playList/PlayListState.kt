package com.example.musium.presentation.ui.playList

import com.example.musium.domain.model.PlaylistTrackItem

data class PlayListState(
    var isLoading: Boolean = false,
    var errorMessage: String? = null,
    var playListTracks: List<PlaylistTrackItem> = emptyList()
)