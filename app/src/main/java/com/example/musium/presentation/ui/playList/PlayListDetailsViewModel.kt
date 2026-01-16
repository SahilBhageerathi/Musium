package com.example.musium.presentation.ui.playList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musium.domain.usecase.FetchPlayListTrackUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayListDetailsViewModel @Inject constructor(
    private val fetchPlayListTrackUseCase: FetchPlayListTrackUseCase
) : ViewModel() {
    private var _playListState = MutableStateFlow(PlayListState())
    val playListState = _playListState.asStateFlow()


    private var _playListEvent = MutableSharedFlow<PlayListEvent>()
    val playListEvent = _playListEvent.asSharedFlow()

    init {
//        onEvent(PlayListEvent.InitializeEvent("5OQQyL0W8aJ43Ri9upaVa9"))
    }


    fun onEvent(event: PlayListEvent) {
        when (event) {
            is PlayListEvent.InitializeEvent -> initialize(event.playListId)

        }
    }

    private fun initialize(playListId: String) {

        viewModelScope.launch {

            runCatching {
                fetchPlayListTrackUseCase(playListId)
            }.onSuccess { track ->
                _playListState.update {
                    it.copy(
                        playListTracks = track
                    )
                }

            }.onFailure { e ->

                _playListState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.message ?: "Failed to fetch PlayList Tracks"
                    )
                }

            }


        }

    }


}