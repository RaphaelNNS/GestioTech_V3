package com.example.gestiotech_v3.presentation.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestiotech_v3.data.repository.IUserRepository
import com.example.gestiotech_v3.data.repository.UserRepositoryFirestore
import com.example.gestiotech_v3.model.repository.FirebaseHandler
import com.example.gestiotech_v3.presentation.ViewModel.screenState.LoginScreenState
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: IUserRepository
) : ViewModel() {

    val screenStateLivedata = MutableLiveData<LoginScreenState>()
    var screenState = LoginScreenState()

    fun onCreate() {
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            screenState.isLoggedIn = true
            updateLiveData()
        }
    }

    fun onClickLogin() {
        viewModelScope.launch {
            screenState.loading = true
            updateLiveData()
            try {
                val result = repository.loginEmailPassword(screenState.email, screenState.password)
                if (result != null) {
                    screenState.isLoggedIn = true
                    updateLiveData()
                }
                screenState.loading = false
                updateLiveData()
            } catch (e: Exception) {
                screenState.message = e.message.toString()
                screenState.loading = false
                updateLiveData()
            }
        }
    }

    private fun updateLiveData() {
        screenStateLivedata.postValue(screenState)
    }
}
