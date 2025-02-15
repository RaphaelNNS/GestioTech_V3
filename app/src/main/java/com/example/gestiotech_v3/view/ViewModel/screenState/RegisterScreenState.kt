package com.example.gestiotech_v3.view.ViewModel.screenState

import androidx.lifecycle.MutableLiveData

data class RegisterScreenState(
    var isLoggedIn: Boolean = false,
    var loading: Boolean = false,
    var password: String = "",
    var confirmPassword: String = "",
    var email: String = "",
    var message: String = "",
)


