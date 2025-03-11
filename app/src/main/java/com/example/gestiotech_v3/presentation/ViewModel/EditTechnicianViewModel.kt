package com.example.gestiotech_v3.presentation.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestiotech_v3.data.repository.ITechnicianRepository
import com.example.gestiotech_v3.model.entities.Technician
import com.example.gestiotech_v3.presentation.ViewModel.screenState.EditTechnicianActivityScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditTechnicianViewModel @Inject constructor(
    val technicianRepository: ITechnicianRepository
) : ViewModel() {

    var screenState = EditTechnicianActivityScreenState()
    val screenStateLiveData: MutableLiveData<EditTechnicianActivityScreenState> = MutableLiveData()

    init {
        screenStateLiveData.value = screenState
    }

    fun setTechnician(technician: Technician) {
        screenState.technician = technician
        updateLiveData()
    }

    fun editClient() {
        var technician = screenState.technician

        screenState.isLoading = true
        updateLiveData()

        viewModelScope.launch {
            kotlin.runCatching {
                technicianRepository.editTechnician(technician)
            }.onSuccess {
                screenState.errorMessage = "Cliente atualizado com sucesso."
                updateLiveData()
            }.onFailure { e ->
                screenState.errorMessage = e.message ?: "Erro desconhecido ao salvar."
                updateLiveData()
            }

            screenState.isLoading = false
            updateLiveData()
        }
    }

    fun updateLiveData() {
        screenStateLiveData.postValue(screenState)
    }

    fun deleteTechnician(){

        var technicianId = screenState.technician.id

        kotlin.runCatching {
            technicianRepository.deleteTechnician(technicianId)
        }.onSuccess {
            screenState.isTechnicianDone = true
            updateLiveData()
        }.onFailure {
            screenState.errorMessage = "Erro ao exlcuir o cliente \n Operação não efetuada"
        }
    }


}
