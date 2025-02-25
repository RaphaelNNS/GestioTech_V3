package com.example.gestiotech_v3.presentation.ViewModel

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gestiotech_v3.R
import com.example.gestiotech_v3.presentation.Fragment.HomeFragment
import com.example.gestiotech_v3.presentation.ViewModel.screenState.HomeActivityScreenState

class HomeViewModel : ViewModel() {

    val DEFAULT_FRAGMENT = HomeFragment()
    val DEFAULT_SCREENSTATE = HomeActivityScreenState(DEFAULT_FRAGMENT, null)

    val screenState: HomeActivityScreenState = DEFAULT_SCREENSTATE
    val screenStateLiveData = MutableLiveData<HomeActivityScreenState>()

    init {
        screenStateLiveData.postValue(DEFAULT_SCREENSTATE)
    }





    fun makeCurrentFragment(fragment: Fragment, bundle: Bundle?){
        if (bundle != null){
            fragment.arguments = bundle
        }
        val fragmentManager = screenState.currentFragment.requireActivity().supportFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.fragment_home, fragment).commit()
    }

    fun updateLiveData() {
        screenStateLiveData.value = screenState
    }
}