package com.example.gestiotech_v3.presentation.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestiotech_v3.data.repository.IClientRepository
import com.example.gestiotech_v3.presentation.ViewModel.screenState.ClientListScreenState
import com.example.gestiotech_v3.presentation.ViewModel.screenState.displayState.ClientDisplayState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientListViewModel @Inject constructor(
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
                screenState.clientDisplayState = ClientDisplayState.Loading
                val clients = clientRepository.getClients()
                screenState.clientDisplayState = ClientDisplayState.Success(clients)
            }catch (e: Exception){
                screenState.clientDisplayState = ClientDisplayState.Error(e)
            }finally {
                updateLiveData()
            }
        }
    }

    fun updateLiveData(){
        screenStateLiveData.postValue(screenState)
    }
}