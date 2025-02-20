package com.example.gestiotech_v3.presentation.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestiotech_v3.model.repository.FirebaseHandler
import com.example.gestiotech_v3.presentation.ViewModel.screenState.ClientListScreenState
import com.example.gestiotech_v3.presentation.ViewModel.screenState.displayState.DisplayState
import kotlinx.coroutines.launch

class ClientListViewModel : ViewModel() {
    private var firebaseHandler: FirebaseHandler = FirebaseHandler()

    val screenStateLiveData = MutableLiveData<ClientListScreenState>()
    val screenState = ClientListScreenState()

    init {
        getClients()
    }
    //.view.Fragment.AddClientFragment
    fun getClients(){
        viewModelScope.launch {
            try {
                screenState.clientlistDisplayState = DisplayState.Loading
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