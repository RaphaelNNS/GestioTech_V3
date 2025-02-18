package com.example.gestiotech_v3.view.ViewModel.screenState.displayState

import com.example.gestiotech_v3.model.entities.Client

sealed interface DisplayState {
    data object Loading: DisplayState
    data class Error(
        val exception: Exception
    ) : DisplayState
    data class Sucess(
        val clientList: List<Client>
    ): DisplayState
}