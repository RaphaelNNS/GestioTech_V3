package com.example.gestiotech_v3.presentation.ViewModel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gestiotech_v3.data.repository.ClientRepositoryFirestore
import com.example.gestiotech_v3.data.repository.IClientRepository
import com.example.gestiotech_v3.presentation.ViewModel.AddClientViewModel

class AddClientViewModelFactory(
    private val  clientRepository: IClientRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddClientViewModel::class.java)){
            return AddClientViewModel(clientRepository) as T
        }else{
            return throw IllegalArgumentException()
        }

    }
}