package com.example.gestiotech_v3.presentation.ViewModel.screenState

import com.example.gestiotech_v3.model.entities.Client

data class EditClientActivityScreenState (
    var client: Client = Client(),
    var isLoading: Boolean = false,
    var errorMessage: String = "",
    var isClientDone: Boolean = false
)
