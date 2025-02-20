package com.example.gestiotech_v3.presentation.ViewModel.screenState

data class RegisterScreenState(
    var isLoggedIn: Boolean = false,
    var loading: Boolean = false,
    var password: String = "",
    var confirmPassword: String = "",
    var email: String = "",
    var message: String = "",
)


