package com.example.gestiotech_v3.presentation.Fragment

import android.graphics.Color
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gestiotech_v3.R
import com.example.gestiotech_v3.data.repository.ClientRepositoryFirestore
import com.example.gestiotech_v3.databinding.FragmentAddClientBinding
import com.example.gestiotech_v3.model.entities.Client
import com.example.gestiotech_v3.presentation.ViewModel.AddClientViewModel
import com.example.gestiotech_v3.presentation.ViewModel.factory.AddClientViewModelFactory
import com.example.gestiotech_v3.presentation.ViewModel.screenState.displayState.UiMessage

class  AddClientFragment : Fragment() {

    private lateinit var binding: FragmentAddClientBinding
    private val viewModel: AddClientViewModel by viewModels {
        AddClientViewModelFactory(ClientRepositoryFirestore())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddClientBinding.inflate(inflater)
        setupUButtons()
        setupObserver()

        return binding.root
    }

    private fun setupObserver() {
        viewModel.addClientListScreenStateLiveData.observe(viewLifecycleOwner) { screenState ->
            when (val messageState = screenState.uiMessage) {
                is UiMessage.Success -> showSuccessMessage(messageState.message)
                is UiMessage.Failure -> showErrorMessage(messageState.message)
                UiMessage.None -> Unit
            }
            showLoading(screenState.isLoading)
        }
    }

    private fun setupUButtons() {
        binding.buttonSaveClient.setOnClickListener{
            val newClient = Client(
                "",
                binding.editTextName.text.toString(),
                binding.editTextDocument.text.toString(),
                binding.editTextAddress.text.toString(),
                binding.editTextPhone.text.toString(),
                binding.editTextDescription.text.toString(),
            )
            if (newClient.name.isBlank()){
                showErrorMessage("O nome não pode estar vazio")
            }else{
                viewModel.addClient(newClient)
            }
        }
        binding.buttonBack.setOnClickListener{
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragment_home, ClientListFragment()).commit()

        }

    }

    private fun showLoading(show: Boolean) {
        requireActivity().runOnUiThread {
            if (show){
                binding.progressBar.visibility = View.VISIBLE

            }else binding.progressBar.visibility = View.GONE

        }
    }

    fun showErrorMessage(messsage: String){
        binding.textErrorMessage.visibility = View.VISIBLE
        binding.textErrorMessage.setTextColor(Color.rgb(252, 3, 3))
        binding.textErrorMessage.text = messsage

    }
    fun showSuccessMessage(messsage: String){
        binding.textErrorMessage.visibility = View.VISIBLE
        binding.textErrorMessage.setTextColor(Color.rgb(0, 179, 48))
        binding.textErrorMessage.text = messsage

    }
}