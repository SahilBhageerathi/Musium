package com.example.musium.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musium.domain.usecase.FetchAlbumsUseCase
import com.example.musium.domain.usecase.GetUserUseCase
import com.example.musium.domain.usecase.SearchTeluguHindiPlayListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val fetchAlbumsUseCase: FetchAlbumsUseCase,
    private val searchTopHindiTeluguPlayListUseCase: SearchTeluguHindiPlayListUseCase
) : ViewModel() {
    private var _homeState = MutableStateFlow(HomeState())
    val homeState = _homeState.asStateFlow()


    private var _homeEvent = MutableSharedFlow<HomeEvent>()
    val homeEvent = _homeEvent.asSharedFlow()

    init {
        onEvent(HomeEvent.InitializeEvent)
    }


    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.InitializeEvent -> initialize()

        }
    }


    private fun initialize() {
        viewModelScope.launch {
            _homeState.update { it.copy(isLoading = true, errorMessage = null) }

            runCatching {
                getUserUseCase()
            }.onSuccess { user ->
                _homeState.update {
                    it.copy(
                        currentUser = user,
                        isLoading = false
                    )
                }
            }.onFailure { e ->
                _homeState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.message ?: "Failed to load user"
                    )
                }
            }


            runCatching {
                fetchAlbumsUseCase()
            }.onSuccess { albums ->
                _homeState.update {
                    it.copy(
                        albums = albums
                    )
                }

            }.onFailure { e ->

                    _homeState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = e.message ?: "Failed to fetch albums"
                        )
                    }

                }



            runCatching {
                searchTopHindiTeluguPlayListUseCase()
            }.onSuccess { playLists ->
                _homeState.update {
                    it.copy(
                        topPlayList = playLists
                    )
                }

            }.onFailure { e ->
                _homeState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.message ?: "Failed to fetch TopPlayLists"
                    )
                }

            }
        }

    }
}