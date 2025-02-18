package com.example.gestiotech_v3.view.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestiotech_v3.model.auth.FirebaseHandler
import com.example.gestiotech_v3.view.ViewModel.screenState.ClientListScreenState
import com.example.gestiotech_v3.view.ViewModel.screenState.displayState.DisplayState
import kotlinx.coroutines.launch

class ClientListViewModel : ViewModel() {
    private var firebaseHandler: FirebaseHandler = FirebaseHandler()

    val screenStateLiveData = MutableLiveData<ClientListScreenState>()
    val screenState = ClientListScreenState()

    init {
            getClients()
    }

    fun getClients(){
        viewModelScope.launch {
            try {
                val clients = firebaseHandler.getClients()
                screenState.clientlistDisplayState = DisplayState.Sucess(clients)
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