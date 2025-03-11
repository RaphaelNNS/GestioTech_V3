package com.example.gestiotech_v3.presentation.ViewModel.screenState

import com.example.gestiotech_v3.presentation.ViewModel.screenState.displayState.UiMessage

data class AddTechnicianScreenState(
    var name: String = "",
    var description: String = "",
    var email: String = "",
    var phoneNumber: String = "",
    var documentNumber: String = "",
    var uiMessage: UiMessage = UiMessage.None,
    var isLoading: Boolean = false
)