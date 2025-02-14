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
import com.example.gestiotech_v3.databinding.ActivityMainBinding
import com.example.gestiotech_v3.view.ViewModel.LoginViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ////////////////////////////////////[BoilerPlate]////////////////////////////////////
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
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
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        loginViewModel.onCreate()
        //Live -> IsLoggedIn
        val isLoggedInLiveData = loginViewModel.isLoggedInLiveData
        isLoggedInLiveData.observe(this){
            if (isLoggedInLiveData.value == true){
                val intent = Intent(this, HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
        //LiveData -> Password
        val passwordLiveData = loginViewModel.passwordLiveData
        passwordLiveData.observe(this) { password ->
        }
        //LiveData -> Email
        val emailLiveData = loginViewModel.emailLiveData
        emailLiveData.observe(this){ email ->
        }
        //LiveData -> ServerResponse
        val responseLiveData = loginViewModel.serverResponseLiveData
        responseLiveData.observe(this){
            val response = responseLiveData.value
            if(response != null){
                showServerResponse(response)
            }
        }
        //LiveData -> Loading
        val loadingLiveData = loginViewModel.loadinLiveData
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
        binding.buttonLogin.setOnClickListener {
            updateOnModel()
            loginViewModel.onClickLogin()
        }
        binding.textRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
    private fun updateOnModel(){
        loginViewModel.email = binding.editTextEmail.text.toString()
        loginViewModel.emailLiveData.value = loginViewModel.email
        loginViewModel.password = binding.editTextPassword.text.toString()
        loginViewModel.serverResponseLiveData.value = loginViewModel.message
    }
}