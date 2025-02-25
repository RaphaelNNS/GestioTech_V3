package com.example.gestiotech_v3.presentation.ViewModel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gestiotech_v3.data.repository.IClientRepository
import com.example.gestiotech_v3.presentation.ViewModel.ClientListViewModel
import com.example.gestiotech_v3.presentation.ViewModel.EditClientViewModel

class EditClientViewModelFactory(
    private val  clientRepository: IClientRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditClientViewModel::class.java)){
            return EditClientViewModel(clientRepository) as T
        }else{
            return throw IllegalArgumentException()
        }

    }
}