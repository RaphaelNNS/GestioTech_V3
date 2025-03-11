package com.example.gestiotech_v3.presentation.ViewModel

import androidx.lifecycle.ViewModel
import com.example.gestiotech_v3.data.repository.IUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private var repository: IUserRepository
) : ViewModel() {

    // Todo(screenState, LiveData)

    fun logOut(){
        repository.logOut()
    }

}