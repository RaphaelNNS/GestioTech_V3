package com.example.gestiotech_v3.presentation.ViewModel.screenState.displayState

sealed interface UiMessage {
    data class Failure(val message: String): UiMessage
    data class Success(val message: String): UiMessage
    object None: UiMessage
}
