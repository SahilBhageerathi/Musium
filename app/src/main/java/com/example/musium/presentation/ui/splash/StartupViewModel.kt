package com.example.musium.presentation.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musium.data.local.AppDatabase
import com.example.musium.domain.usecase.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull

@HiltViewModel
class StartupViewModel @Inject constructor(
    private val database: AppDatabase,
) : ViewModel() {
    private val _state = MutableStateFlow(StartupState.Booting)
    val state: StateFlow<StartupState> = _state

    init {
        viewModelScope.launch {
            withTimeoutOrNull(1_200) {
                coroutineScope {
                    launch { database.openHelper.writableDatabase }
                }
            }
            _state.value = StartupState.Ready
        }
    }
}

enum class StartupState {
    Booting,
    Ready
}