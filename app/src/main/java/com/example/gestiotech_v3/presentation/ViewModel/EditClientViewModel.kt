package com.example.gestiotech_v3.presentation.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestiotech_v3.data.repository.IClientRepository
import com.example.gestiotech_v3.model.entities.Client
import com.example.gestiotech_v3.presentation.ViewModel.screenState.EditClientActivityScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditClientViewModel @Inject constructor(
    private val clientRepository: IClientRepository
) : ViewModel() {

    private var screenState = EditClientActivityScreenState()
    val screenStateLiveData: MutableLiveData<EditClientActivityScreenState> = MutableLiveData()

    init {
        screenStateLiveData.value = screenState
    }

    fun setClient(client: Client) {
        screenState.client = client
        updateLiveData()
    }

    fun editClient() {
        var client = screenState.client

        screenState.isLoading = true
        updateLiveData()

        viewModelScope.launch {
            kotlin.runCatching {
                clientRepository.editCLients(client)
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

    private fun updateLiveData() {
        val currentState = screenState
        screenStateLiveData.postValue(screenState)
    }

    fun deleteClient(){

        var clientId = screenState.client.id

        kotlin.runCatching {
            clientRepository.deleteCLients(clientId)
        }.onSuccess {
            screenState.isClientDone = true
            updateLiveData()
        }.onFailure {
            screenState.errorMessage = "Erro ao exlcuir o cliente \n Operação não efetuada"
        }
    }


}
