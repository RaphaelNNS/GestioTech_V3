package com.example.gestiotech_v3.presentation.Activity

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.gestiotech_v3.R
import com.example.gestiotech_v3.databinding.ActivityHomeBinding
import com.example.gestiotech_v3.presentation.Fragment.ClientListFragment
import com.example.gestiotech_v3.presentation.Fragment.HomeFragment
import com.example.gestiotech_v3.presentation.Fragment.SettingsFragment
import com.example.gestiotech_v3.presentation.ViewModel.TecListFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

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

        val homeFragment = HomeFragment()
        val settingsFragment = SettingsFragment()
        val tecListFragment = TecListFragment()
        val clientListFragment = ClientListFragment()

        makeCurrentFragment(homeFragment, null)


        binding.homeNav.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.ic_home -> makeCurrentFragment(homeFragment, null)
                R.id.ic_settings -> makeCurrentFragment(settingsFragment, null)
                R.id.ic_tecList -> makeCurrentFragment(tecListFragment, null)
                R.id.ic_clients -> makeCurrentFragment(clientListFragment, null)
            }
            true
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