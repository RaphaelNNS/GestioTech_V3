package com.example.gestiotech_v3.presentation.ViewModel.screenState

data class AddClientScreenState(
    var name: String = "",
    var documentNumber: String = "",
    var adrress: String = "",
    var phoneNumber: String = "",
    var description: String = "",
    var errorMessage: String = "",
    var isLoading: Boolean = false
)