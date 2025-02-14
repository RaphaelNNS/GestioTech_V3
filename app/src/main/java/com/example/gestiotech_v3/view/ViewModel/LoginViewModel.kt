package com.example.gestiotech_v3.view.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestiotech_v3.model.auth.FirebaseHandler
import com.example.gestiotech_v3.view.ViewModel.screenState.DisplayState
import com.example.gestiotech_v3.view.ViewModel.screenState.LoginScreenState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    var firebaseHandler: FirebaseHandler = FirebaseHandler()

    val screenStateLivedata = MutableLiveData<LoginScreenState>()
    var screenState = LoginScreenState(displayState = DisplayState.Content)

    fun onCreate(){
        val user = FirebaseAuth.getInstance().currentUser
        if(user != null){
            screenState.isLoggedIn = true
            updateLiveData()
        }
    }

    fun onClickLogin() {
        viewModelScope.launch{
            screenState.loading = true
            updateLiveData()
            try {
                var result = firebaseHandler.loginEmailPassword(screenState.email, screenState.password)
                if (result != null){
                    screenState.isLoggedIn = true
                    updateLiveData()
                }
                screenState.loading = false
                updateLiveData()

            }catch (e: Exception){
                screenState.message = e.message.toString()
                screenState.loading = false
                updateLiveData()


            }
        }
    }

    fun updateLiveData(){
        screenStateLivedata.postValue(screenState)
    }
}