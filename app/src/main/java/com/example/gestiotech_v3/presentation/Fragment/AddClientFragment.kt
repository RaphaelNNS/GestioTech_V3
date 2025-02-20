package com.example.gestiotech_v3.presentation.Fragment

import android.graphics.Color
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gestiotech_v3.databinding.FragmentAddClientBinding
import com.example.gestiotech_v3.model.entities.Client
import com.example.gestiotech_v3.presentation.ViewModel.AddClientViewModel

class  AddClientFragment : Fragment() {

    private lateinit var binding: FragmentAddClientBinding


    companion object {
        fun newInstance() = AddClientFragment()
    }

    private val viewModel: AddClientViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.addClientListScreenStateLiveData.observe(requireActivity()){ screenState ->
            showErrorMessage(screenState.errorMessage)
            if(screenState.isLoading){
                showLoading(true)
            }else(
                showLoading(false)
            )

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddClientBinding.inflate(layoutInflater)
        setutUButtons()
        return binding.root
    }

    private fun setutUButtons() {
        binding.buttonSaveClient.setOnClickListener{
            val newClient = Client(
                binding.editTextName.text.toString(),
                binding.editTextDocument.text.toString(),
                binding.editTextAddress.text.toString(),
                binding.editTextPhone.text.toString(),
                binding.editTextDescription.text.toString()
            )
            viewModel.addClient(newClient)
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
        binding.textErrorMessage.setTextColor(Color.rgb(252, 3, 3))
        binding.textErrorMessage.text = messsage
    }
    fun showSucessMessage(messsage: String){
        binding.textErrorMessage.setTextColor(Color.rgb(0, 179, 48))
        binding.textErrorMessage.text = messsage
    }
}