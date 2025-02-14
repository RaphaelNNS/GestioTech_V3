package com.example.gestiotech_v3.view.Fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gestiotech_v3.R
import com.example.gestiotech_v3.databinding.FragmentSettingsBinding
import com.example.gestiotech_v3.model.auth.FirebaseHandler
import com.example.gestiotech_v3.view.Activity.HomeActivity
import com.example.gestiotech_v3.view.Activity.MainActivity
import com.example.gestiotech_v3.view.ViewModel.SettingsViewModel

class SettingsFragment : Fragment() {

    private lateinit var firebaseHandler: FirebaseHandler

    companion object {
        fun newInstance() = SettingsFragment()
    }

    private val viewModel: SettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firebaseHandler = FirebaseHandler()

        // TODO: Use the ViewModel

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bind = FragmentSettingsBinding.inflate(layoutInflater)
        bind.btnLogout.setOnClickListener{logOut()}

        return bind.root
    }

    fun logOut() {
        firebaseHandler.logOut()
        val intent = Intent(requireContext(), MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}