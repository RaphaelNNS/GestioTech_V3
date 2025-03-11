package com.example.gestiotech_v3.presentation.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestiotech_v3.data.repository.ITechnicianRepository
import com.example.gestiotech_v3.presentation.ViewModel.screenState.TecListScreenState
import com.example.gestiotech_v3.presentation.ViewModel.screenState.displayState.TechnicianDisplayState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TecListViewModel @Inject constructor(
    private val  repository: ITechnicianRepository
) : ViewModel(){

    val screenStateLiveData = MutableLiveData<TecListScreenState>()
    val screenState: TecListScreenState = TecListScreenState()

    init {
        getTechnicians()
    }



    fun getTechnicians() {
        viewModelScope.launch {
            try {
                screenState.displayState = TechnicianDisplayState.Loading
                val tecList = repository.getTechnicians()
                screenState.displayState = TechnicianDisplayState.Success(tecList)
            }catch (e: Exception){
                screenState.displayState = TechnicianDisplayState.Error(e)
            }finally {
                updateLiveData()
            }
        }
    }
    fun updateLiveData(){
        screenStateLiveData.postValue(screenState)
    }

}