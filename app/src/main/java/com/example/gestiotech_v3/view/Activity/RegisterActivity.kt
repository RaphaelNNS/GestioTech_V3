package com.example.gestiotech_v3.view.Activity

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.gestiotech_v3.R
import com.example.gestiotech_v3.databinding.ActivityRegisterBinding
import com.example.gestiotech_v3.view.ViewModel.LoginViewModel
import com.example.gestiotech_v3.view.ViewModel.RegisterViewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        ////////////////////////////////////[BoilerPlate]////////////////////////////////////
        setupButtons()
        //ViewModel
        registerViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        val hasLoggedLiveData = registerViewModel.hasLoggedLiveData
        hasLoggedLiveData.observe(this){
            if (hasLoggedLiveData.value == true){
                registerViewModel.hasLogged = false
                registerViewModel.hasLoggedLiveData.value = registerViewModel.hasLogged
                startActivity(Intent(this, LoginViewModel::class.java))

            }
        }
        //LiveData -> Password
        val passwordLiveData = registerViewModel.passwordLiveData
        passwordLiveData.observe(this) { password ->
        }
        //LiveData -> Email
        val emailLiveData = registerViewModel.emailLiveData
        emailLiveData.observe(this){ email ->
        }
        //LiveData -> ServerResponse
        val responseLiveData = registerViewModel.serverResponseLiveData
        responseLiveData.observe(this){
            val response = responseLiveData.value
            if(response != null){
                showServerResponse(response)
            }
        }
        //LiveData -> Loading
        val loadingLiveData = registerViewModel.loadinLiveData
        loadingLiveData.observe(this){ isLoading ->
            if(isLoading) showLoading()
            if(isLoading == false) hideLoading()
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
        registerViewModel.email = binding.editTextEmail.text.toString()
        registerViewModel.emailLiveData.value = registerViewModel.email
        registerViewModel.password = binding.editTextPassword.text.toString()
        registerViewModel.serverResponseLiveData.value = registerViewModel.message
    }
}