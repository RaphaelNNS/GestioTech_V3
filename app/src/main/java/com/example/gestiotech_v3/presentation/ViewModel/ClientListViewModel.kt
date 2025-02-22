package com.example.gestiotech_v3.presentation.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestiotech_v3.data.repository.IClientRepository
import com.example.gestiotech_v3.model.repository.FirebaseHandler
import com.example.gestiotech_v3.presentation.ViewModel.screenState.ClientListScreenState
import com.example.gestiotech_v3.presentation.ViewModel.screenState.displayState.DisplayState
import kotlinx.coroutines.launch

class ClientListViewModel(
    private val  clientRepository: IClientRepository
) : ViewModel() {

    val screenStateLiveData = MutableLiveData<ClientListScreenState>()
    val screenState = ClientListScreenState()

    init {
        getClients()
    }
    fun getClients(){
        viewModelScope.launch {
            try {
                screenState.clientlistDisplayState = DisplayState.Loading
                val clients = clientRepository.getClients()
                screenState.clientlistDisplayState = DisplayState.Success(clients)
            }catch (e: Exception){
                screenState.clientlistDisplayState = DisplayState.Error(e)
            }finally {
                updateLiveData()
            }
        }
    }

    fun updateLiveData(){
        screenStateLiveData.postValue(screenState)
    }
}