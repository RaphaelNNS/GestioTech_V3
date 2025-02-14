package com.example.gestiotech_v3.view.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestiotech_v3.model.auth.FirebaseHandler
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    var firebaseHandler: FirebaseHandler = FirebaseHandler()

    val isLoggedInLiveData = MutableLiveData<Boolean>()
    var isLoggedIn = false

    val loadinLiveData = MutableLiveData<Boolean>()
    var loading = false

    val passwordLiveData = MutableLiveData<String>()
    var password = ""

    val confirmedPasswordLiveData = MutableLiveData<String>()
    var confirmedPassword = ""

    val emailLiveData = MutableLiveData<String>()
    var email = ""

    val serverResponseLiveData = MutableLiveData<String>()
    var message = ""


    fun onCreate(){
        val user = FirebaseAuth.getInstance().currentUser
        if(user != null){
            isLoggedIn = true
            isLoggedInLiveData.value = isLoggedIn
        }
    }

    fun onClickLogin() {
        viewModelScope.launch{
            loading = true
            loadinLiveData.postValue(loading)
            try {
                var result = firebaseHandler.loginEmailPassword(email, password)
                if (result != null){
                    isLoggedIn = true
                    isLoggedInLiveData.postValue(isLoggedIn)
                }
                loading = false
                loadinLiveData.postValue(loading)

            }catch (e: Exception){
                message = e.message.toString()
                serverResponseLiveData.postValue(message)
                loading = false
                loadinLiveData.postValue(loading)
            }
        }
    }

}