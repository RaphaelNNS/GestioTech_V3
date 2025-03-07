package com.example.gestiotech_v3.presentation.Activity

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.gestiotech_v3.R
import com.example.gestiotech_v3.databinding.ActivityRegisterBinding
import com.example.gestiotech_v3.presentation.ViewModel.LoginViewModel
import com.example.gestiotech_v3.presentation.ViewModel.RegisterViewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val registerViewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupButtons()
        setupObservers()
    }

    private fun setupButtons(){
        binding.buttonRegister.setOnClickListener {
            updateOnModel()
            registerViewModel.onClickRegister()
        }
        binding.textLogin.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun updateOnModel(){
        registerViewModel.screenState.email = binding.editTextEmail.text.toString()
        registerViewModel.screenState.password = binding.editTextPassword.text.toString()
        registerViewModel.screenState.confirmPassword = binding.editTextConfirmPassword.text.toString()
        registerViewModel.screenState.name = binding.editTextUserName.text.toString()
        registerViewModel.updateLiveData()
    }

    private fun setupObservers(){
        val screenStateLiveData = registerViewModel.screenStateLiveData
        screenStateLiveData.observe(this){
            if (screenStateLiveData.value?.isLoggedIn == true){
                val intent = Intent(this, HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(Intent(this, LoginViewModel::class.java))
            }
            val response = screenStateLiveData.value?.message
            if(response != null){
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


    private fun showLoading(){
        binding.progressBar.visibility = View.VISIBLE
    }
    private fun hideLoading(){
        binding.progressBar.visibility = View.GONE
    }
    fun showServerResponse(message: String){
        binding.textReturn.visibility = View.VISIBLE
        binding.textReturn.text = message
    }
    private fun unableButtons(){
        binding.buttonRegister.isEnabled = false
        binding.textLogin.isEnabled = false
    }
    private fun ableButtons(){
        binding.buttonRegister.isEnabled = true
        binding.textLogin.isEnabled = true
    }


}