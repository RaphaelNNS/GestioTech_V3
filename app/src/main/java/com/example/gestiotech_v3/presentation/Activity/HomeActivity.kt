package com.example.gestiotech_v3.presentation.Activity

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.gestiotech_v3.R
import com.example.gestiotech_v3.databinding.ActivityHomeBinding
import com.example.gestiotech_v3.presentation.Fragment.ClientListFragment
import com.example.gestiotech_v3.presentation.Fragment.HomeFragment
import com.example.gestiotech_v3.presentation.Fragment.SettingsFragment
import com.example.gestiotech_v3.presentation.ViewModel.HomeViewModel
import com.example.gestiotech_v3.presentation.Fragment.TecListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private  val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupObserver()

        val settingsFragment = SettingsFragment()

        binding.homeNav.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.ic_contracts -> viewModel.screenState.currentFragment = HomeFragment()
                R.id.ic_settings -> viewModel.screenState.currentFragment = settingsFragment
                R.id.ic_tecList -> viewModel.screenState.currentFragment = TecListFragment()
                R.id.ic_clients -> viewModel.screenState.currentFragment = ClientListFragment()
            }
            viewModel.updateLiveData()
            true
        }
    }

    private fun setupObserver() {
        viewModel.screenStateLiveData.observe(this){ screenState ->
            makeCurrentFragment(screenState.currentFragment, screenState.bundle )
        }
    }


    fun makeCurrentFragment(fragment: Fragment, bundle: Bundle?){
        if (bundle != null){
            fragment.arguments = bundle
        }
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.fragment_home, fragment).commit()
    }
}