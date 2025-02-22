package com.example.gestiotech_v3.presentation.ViewModel.screenState.displayState

import com.example.gestiotech_v3.model.entities.Client

sealed interface DisplayState {
    data object Loading: DisplayState
    data class Error(
        val exception: Exception
    ) : DisplayState
    data class Success(
        val clientList: List<Client>
    ): DisplayState
}