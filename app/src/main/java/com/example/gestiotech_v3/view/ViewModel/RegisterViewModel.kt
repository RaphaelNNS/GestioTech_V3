package com.example.gestiotech_v3.view.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestiotech_v3.model.auth.FirebaseHandler
import kotlinx.coroutines.launch

class RegisterViewModel: ViewModel() {

    var firebaseHandler: FirebaseHandler = FirebaseHandler()

    val hasLoggedLiveData = MutableLiveData<Boolean>()
    var hasLogged = false

    val loadinLiveData = MutableLiveData<Boolean>()
    var loading = false

    val passwordLiveData = MutableLiveData<String>()
    var password = ""

    val emailLiveData = MutableLiveData<String>()
    var email = ""

    val serverResponseLiveData = MutableLiveData<String>()
    var message = ""


    fun onClickRegister() {
        viewModelScope.launch{
            loading = true
            loadinLiveData.postValue(loading)
            try {
                var result = firebaseHandler.registerEmailPassword(email, password)
                message = result.toString()
                serverResponseLiveData.postValue(message)
                loading = false
                loadinLiveData.postValue(loading)
                hasLogged = true
                hasLoggedLiveData.postValue(hasLogged)
            }catch (e: Exception){
                message = e.message.toString()
                serverResponseLiveData.postValue(message)
                loading = false
                loadinLiveData.postValue(loading)
            }
        }
    }
}
