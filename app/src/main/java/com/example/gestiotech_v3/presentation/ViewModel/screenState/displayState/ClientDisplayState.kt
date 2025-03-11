package com.example.gestiotech_v3.presentation.ViewModel.screenState.displayState

import com.example.gestiotech_v3.model.entities.Client

sealed interface ClientDisplayState {
    data object Loading: ClientDisplayState
    data class Error(
        val exception: Exception
    ) : ClientDisplayState
    data class Success(
        val clientList: List<Client>
    ): ClientDisplayState
}