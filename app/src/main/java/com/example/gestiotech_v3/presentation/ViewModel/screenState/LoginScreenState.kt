package com.example.gestiotech_v3.presentation.ViewModel.screenState

data class LoginScreenState(
    var isLoggedIn: Boolean = false,
    var loading: Boolean = false,
    var email: String = "",
    var password: String = "",
    var message: String = "",
)


