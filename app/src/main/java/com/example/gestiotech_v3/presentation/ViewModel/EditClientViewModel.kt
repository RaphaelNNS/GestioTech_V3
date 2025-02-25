package com.example.gestiotech_v3.presentation.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestiotech_v3.data.repository.IClientRepository
import com.example.gestiotech_v3.presentation.Activity.EditClientActivity
import com.example.gestiotech_v3.presentation.ViewModel.screenState.EditClientActivityScreenState
import kotlinx.coroutines.launch

class EditClientViewModel(
    private val clientRepository: IClientRepository
) : ViewModel() {

    var screenState = EditClientActivityScreenState()
    var screenStateLiveData = MutableLiveData<EditClientActivityScreenState>()

    init {
        screenStateLiveData.value = screenState // Inicializa o LiveData corretamente
    }

    fun editClient() {
        updateLivedata()
        viewModelScope.launch {
            screenState.isLoading = true
            updateLivedata()

            val client = screenState.client
            if (client == null) {
                screenState.errorMessage = "Erro: Cliente não encontrado."
                screenState.isLoading = false
                updateLivedata()
                return@launch
            }

            kotlin.runCatching {
                clientRepository.editCLients(client, client.id)
            }.onSuccess {
                screenState.errorMessage = "Cliente atualizado com sucesso."
            }.onFailure { e ->
                screenState.errorMessage = e.message ?: "Erro desconhecido ao salvar."
            }

            screenState.isLoading = false
            updateLivedata()
        }
    }

    private fun updateLivedata() {
        screenStateLiveData.postValue(screenState)
    }
}