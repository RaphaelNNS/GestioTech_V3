package com.example.gestiotech_v3.presentation.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestiotech_v3.model.repository.FirebaseHandler
import com.example.gestiotech_v3.model.entities.Client
import com.example.gestiotech_v3.presentation.ViewModel.screenState.AddClientScreenState
import kotlinx.coroutines.launch

class AddClientViewModel : ViewModel() {

    var firebaseHandler: FirebaseHandler = FirebaseHandler()

    val addClientListScreenStateLiveData = MutableLiveData<AddClientScreenState>()
    val addClientListScreenState = AddClientScreenState()

    fun addClient(client: Client){
        if (!checkClient(client)) return
        viewModelScope.launch {
            addClientListScreenState.isLoading = true
            updateLivedData()
            try {
                firebaseHandler.addClient(client)
            }catch (e: Exception){
                addClientListScreenState.errorMessage = e.message.toString()
                updateLivedData()
            }finally {
                addClientListScreenState.isLoading = false
                updateLivedData()
            }
        }
    }

    fun checkClient(client: Client): Boolean {
        if(client.name.isEmpty()) {
            return false
        }
        return true
    }




    fun updateLivedData(){
        addClientListScreenStateLiveData.value = addClientListScreenState
    }
}