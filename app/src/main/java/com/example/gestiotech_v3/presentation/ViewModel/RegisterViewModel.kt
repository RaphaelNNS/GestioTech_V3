package com.example.gestiotech_v3.presentation.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestiotech_v3.model.repository.FirebaseHandler
import com.example.gestiotech_v3.presentation.ViewModel.screenState.RegisterScreenState
import kotlinx.coroutines.launch

class RegisterViewModel: ViewModel() {

    var firebaseHandler: FirebaseHandler = FirebaseHandler()

    val screenStateLiveData = MutableLiveData<RegisterScreenState>()
    var screenState = RegisterScreenState()

    fun onClickRegister() {

        if(!checkPassword(screenState.password, screenState.confirmPassword)){
            screenState.message = "As senhas não coincidem"
        }
        viewModelScope.launch{
            screenState.loading = true
            updateLiveData()
            try {
                var result = firebaseHandler.registerEmailPassword(screenState.email, screenState.password)
                screenState.message = result.toString()
                screenState.isLoggedIn = true
            }catch (e: Exception){
                screenState.message = e.message.toString()
            }finally {
                screenState.loading = false
                updateLiveData()
            }
        }
    }

    fun checkPassword(firstPassword: String, secondPassword: String): Boolean{
        return (firstPassword == secondPassword)
    }

    fun updateLiveData(){
        screenStateLiveData.postValue(screenState)
    }
}
