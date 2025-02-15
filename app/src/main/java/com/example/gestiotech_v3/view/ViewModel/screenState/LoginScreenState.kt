package com.example.gestiotech_v3.view.ViewModel.screenState

import androidx.lifecycle.MutableLiveData

data class LoginScreenState(
    var isLoggedIn: Boolean = false,
    var loading: Boolean = false,
    var email: String = "",
    var password: String = "",
    var message: String = "",
)


