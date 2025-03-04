package com.example.gestiotech_v3.presentation.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestiotech_v3.data.repository.ClientRepositoryFirestore
import com.example.gestiotech_v3.data.repository.IClientRepository
import com.example.gestiotech_v3.model.entities.Client
import com.example.gestiotech_v3.presentation.ViewModel.screenState.AddClientScreenState
import com.example.gestiotech_v3.presentation.ViewModel.screenState.displayState.UiMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddClientViewModel @Inject constructor(
    private val  clientRepository: IClientRepository
) : ViewModel() {


    val addClientListScreenStateLiveData = MutableLiveData<AddClientScreenState>()
    val screenState = AddClientScreenState()

    init {
        screenState.isLoading = true
        screenState.uiMessage = UiMessage.None
    }

    fun addClient(client: Client){
        viewModelScope.launch {
            screenState.isLoading = true
            kotlin.runCatching {
                clientRepository.addClient(client)
            }.onSuccess { client ->
                showSuccess(client)
            }.onFailure { e ->
                showError(e)
            }
        }
    }

    fun cleanScreenStateFields(){
        screenState.adress = ""
        screenState.name = ""
        screenState.description = ""
        screenState.documentNumber = ""
        screenState.phoneNumber = ""
    }

    fun updateLivedData(){
        addClientListScreenStateLiveData.value = screenState
    }

    fun showError(e: Throwable){
        screenState.isLoading = false
        screenState.uiMessage = UiMessage.Failure(e.message.toString())
        updateLivedData()
    }

    fun showSuccess(client: Client){
        screenState.isLoading = false
        cleanScreenStateFields()
        screenState.uiMessage = UiMessage.Success("CLIENT ${client.name} SUCCESSFULLY ADDED")
        updateLivedData()
    }
}