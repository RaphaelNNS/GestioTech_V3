package com.example.gestiotech_v3.presentation.ViewModel.screenState

import com.example.gestiotech_v3.model.entities.Client
import com.example.gestiotech_v3.model.entities.Technician

data class EditTechnicianActivityScreenState (
    var technician: Technician = Technician(),
    var isLoading: Boolean = false,
    var errorMessage: String = "",
    var isTechnicianDone: Boolean = false
)
