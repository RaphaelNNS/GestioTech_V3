package com.example.gestiotech_v3.presentation.ViewModel.screenState

import com.example.gestiotech_v3.presentation.ViewModel.screenState.displayState.UiMessage

data class AddClientScreenState(
    var name: String = "",
    var documentNumber: String = "",
    var adress: String = "",
    var phoneNumber: String = "",
    var description: String = "",
    var uiMessage: UiMessage = UiMessage.None,
    var isLoading: Boolean = false
)