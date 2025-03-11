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
import com.example.gestiotech_v3.databinding.FragmentAddTechnicianBinding
import com.example.gestiotech_v3.model.entities.Client
import com.example.gestiotech_v3.model.entities.Technician
import com.example.gestiotech_v3.presentation.ViewModel.AddClientViewModel
import com.example.gestiotech_v3.presentation.ViewModel.AddTechnicianViewModel
import com.example.gestiotech_v3.presentation.ViewModel.screenState.displayState.UiMessage
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class  AddTechnicianFragment : Fragment() {

    private lateinit var binding: FragmentAddTechnicianBinding
    private val viewModel: AddTechnicianViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddTechnicianBinding.inflate(inflater)
        setupUButtons()
        setupObserver()

        return binding.root
    }

    private fun setupObserver() {
        viewModel.addTechnicianScreenStateLiveData.observe(viewLifecycleOwner) { screenState ->
            when (val messageState = screenState.uiMessage) {
                is UiMessage.Success -> showSuccessMessage(messageState.message)
                is UiMessage.Failure -> showErrorMessage(messageState.message)
                UiMessage.None -> Unit
            }
            showLoading(screenState.isLoading)
        }
    }

    private fun setupUButtons() {
        binding.buttonSaveTechnician.setOnClickListener{
            val newTechnician = Technician(
                binding.editTextName.text.toString(),
                binding.editTextDocument.text.toString(),
                binding.editTextEmail.text.toString(),
                binding.editTextPhone.text.toString(),
                binding.editTextDescription.text.toString(),
                emptyList(),
                ""

            )
            if (newTechnician.name.isBlank()){
                showErrorMessage("O nome não pode estar vazio")
            }else{
                viewModel.addTechnician(newTechnician)
            }
        }
        binding.buttonBack.setOnClickListener{
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragment_home, TecListFragment()).commit()

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