package com.example.gestiotech_v3.presentation.ViewModel.screenState.displayState

import com.example.gestiotech_v3.model.entities.Technician

sealed interface TechnicianDisplayState {
    data object Loading: TechnicianDisplayState
    data class Error(
        val exception: Exception
    ) : TechnicianDisplayState
    data class Success(
        val technicianList: List<Technician>
    ): TechnicianDisplayState
}