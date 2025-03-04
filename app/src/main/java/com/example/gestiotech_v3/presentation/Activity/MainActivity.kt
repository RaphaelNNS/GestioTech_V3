package com.example.gestiotech_v3.presentation.Activity

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.gestiotech_v3.R
import com.example.gestiotech_v3.databinding.ActivityMainBinding
import com.example.gestiotech_v3.presentation.ViewModel.LoginViewModel
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupButtons()
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        loginViewModel.onCreate()
        setupObservers()
    }

    private fun setupButtons(){
        binding.buttonLogin.setOnClickListener {
            updateOnModel()
            loginViewModel.onClickLogin()
        }
        binding.textRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }


        private fun setupObservers(){
            val screenStateLiveData = loginViewModel.screenStateLivedata
            screenStateLiveData.observe(this){
                if (screenStateLiveData.value?.isLoggedIn == true){
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
                val response = screenStateLiveData.value?.message
                if(!response.isNullOrEmpty()){
                    showServerResponse(response)
                }
                if(screenStateLiveData.value?.loading == true) {
                    unableButtons()
                    showLoading()
                }
                if(screenStateLiveData.value?.loading == false){
                    hideLoading()
                    ableButtons()
                }
            }
    }





    private fun updateOnModel(){
        val screenState = loginViewModel.screenState
        screenState.email = binding.editTextEmail.text.toString()
        screenState.password = binding.editTextPassword.text.toString()
        loginViewModel.screenStateLivedata.value = screenState
    }

    private fun showLoading(){
        binding.progressBar.visibility = View.VISIBLE
    }
    private fun hideLoading(){
        binding.progressBar.visibility = View.GONE
    }
    private fun unableButtons(){
        binding.buttonLogin.isEnabled = false
        binding.buttonGmail.isEnabled = false
        binding.textRegister.isEnabled = false
    }
    private fun ableButtons(){
        binding.buttonLogin.isEnabled = true
        binding.buttonGmail.isEnabled = true
        binding.textRegister.isEnabled = true
    }
    private fun showServerResponse(message: String){
        binding.textReturn.visibility = View.VISIBLE
        binding.textReturn.text = message
    }

}