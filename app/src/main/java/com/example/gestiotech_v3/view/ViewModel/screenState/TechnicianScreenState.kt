package com.example.gestiotech_v3.view.ViewModel.screenState

import androidx.lifecycle.MutableLiveData

data class TechnicianScreenState(
    var isLoggedIn: Boolean = false,
    var loading: Boolean = false,
    var password: String = "",
    var email: String = "",
    var message: String = "",
)


