package com.example.gestiotech_v3.presentation.ViewModel

import androidx.lifecycle.ViewModel
import com.example.gestiotech_v3.data.repository.IUserRepository
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private var repository: IUserRepository
) : ViewModel() {

    // Todo(screenState, LiveData)

    fun logOut(){
        repository.logOut()
    }

}