package com.example.gestiotech_v3.presentation.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestiotech_v3.data.repository.ITechnicianRepository
import com.example.gestiotech_v3.model.entities.Client
import com.example.gestiotech_v3.model.entities.Technician
import com.example.gestiotech_v3.presentation.ViewModel.screenState.AddClientScreenState
import com.example.gestiotech_v3.presentation.ViewModel.screenState.AddTechnicianScreenState
import com.example.gestiotech_v3.presentation.ViewModel.screenState.displayState.UiMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTechnicianViewModel @Inject constructor(
    private val  technicianRepository: ITechnicianRepository
) : ViewModel() {


    val addTechnicianScreenStateLiveData = MutableLiveData<AddTechnicianScreenState>()
    val screenState = AddTechnicianScreenState()

    init {
        screenState.isLoading = true
        screenState.uiMessage = UiMessage.None
    }

    fun addTechnician(technician: Technician){
        viewModelScope.launch {
            screenState.isLoading = true
            kotlin.runCatching {
                technicianRepository.addTechnician(technician)
            }.onSuccess { tec ->
                showSuccess(tec)
            }.onFailure { e ->
                showError(e)
            }
        }
    }

    fun cleanScreenStateFields(){
        screenState.email = ""
        screenState.name = ""
        screenState.description = ""
        screenState.documentNumber = ""
        screenState.phoneNumber = ""
    }

    fun updateLivedData(){
        addTechnicianScreenStateLiveData.value = screenState
    }

    fun showError(e: Throwable){
        screenState.isLoading = false
        screenState.uiMessage = UiMessage.Failure(e.message.toString())
        updateLivedData()
    }

    fun showSuccess(technician: Technician){
        screenState.isLoading = false
        cleanScreenStateFields()
        screenState.uiMessage = UiMessage.Success("TECHNICIAN ${technician.name} SUCCESSFULLY ADDED")
        updateLivedData()
    }
}