package com.example.gestiotech_v3.presentation.ViewModel.screenState

import com.example.gestiotech_v3.model.entities.Client

data class EditClientActivityScreenState (
    var client: Client? = null,
    var isLoading: Boolean = false,
    var errorMessage: String = ""
)
