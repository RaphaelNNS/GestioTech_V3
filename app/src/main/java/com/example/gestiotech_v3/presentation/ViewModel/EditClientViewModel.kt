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

    private val screenState = MutableLiveData(EditClientActivityScreenState())
    val screenStateLiveData: MutableLiveData<EditClientActivityScreenState> = screenState

    fun setClient(client: Client) {
        screenState.value = screenState.value?.copy(client = client)
    }

    fun editClient() {
        val currentState = screenState.value ?: return
        val client = currentState.client ?: run {
            updateState(errorMessage = "Erro: Cliente não encontrado.")
            return
        }

        updateState(isLoading = true)

        viewModelScope.launch {
            kotlin.runCatching {
                clientRepository.editCLients(client)
            }.onSuccess {
                updateState(errorMessage = "Cliente atualizado com sucesso.")
            }.onFailure { e ->
                updateState(errorMessage = e.message ?: "Erro desconhecido ao salvar.")
            }

            updateState(isLoading = false)
        }
    }

    private fun updateState(
        isLoading: Boolean? = null,
        errorMessage: String? = null
    ) {
        val currentState = screenState.value ?: return
        screenState.postValue(
            currentState.copy(
                isLoading = isLoading ?: currentState.isLoading,
                errorMessage = errorMessage ?: currentState.errorMessage
            )
        )
    }
}
