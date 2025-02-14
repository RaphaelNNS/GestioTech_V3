package com.example.gestiotech_v3.view.ViewModel.screenState

sealed interface DisplayState {
    object Loading : DisplayState
    object Error : DisplayState
    object Content : DisplayState
}
