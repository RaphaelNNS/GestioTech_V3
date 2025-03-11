package com.example.gestiotech_v3.presentation.Activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.semantics.text
import androidx.lifecycle.lifecycleScope
import com.example.gestiotech_v3.databinding.ActivityEditTechnicianBinding
import com.example.gestiotech_v3.model.entities.Client
import com.example.gestiotech_v3.model.entities.Technician
import com.example.gestiotech_v3.presentation.ViewModel.EditTechnicianViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class EditTechnicianActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditTechnicianBinding
    private var technicianId: String? = null
    private val viewModel: EditTechnicianViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditTechnicianBinding.inflate(layoutInflater)
        setContentView(binding.root)

        technicianId = intent.getStringExtra("technicianId")
        if (technicianId == null) {
            binding.textErrorMessage.text = "Technician ID is missing."
            finish()
        }

        setupObserver()
        setupButtons()
        fetchTechnicianData()
    }

    private fun fetchTechnicianData() {
        lifecycleScope.launch {
            val result = viewModel.technicianRepository.getTechnician(technicianId!!)
            if (result != null) {
                fillEditTexts(result)
            }
        }
    }

    private fun setupObserver() {
        viewModel.screenStateLiveData.observe(this) { screenState ->
            if (screenState.isTechnicianDone) finish()
            binding.textErrorMessage.text = screenState.errorMessage
            showLoading(screenState.isLoading)
            freezeInterface(screenState.isLoading)
            if (screenState.technician != null) {
                fillEditTexts(screenState.technician)
            }
        }
    }

    private fun showLoading(visibility: Boolean) {
        binding.progressBar.visibility = if (visibility) View.VISIBLE else View.GONE
    }

    private fun freezeInterface(visibility: Boolean) {
        binding.loadingOverlay.visibility = if (visibility) View.VISIBLE else View.GONE
    }

    private fun setupButtons() {
        binding.buttonBack.setOnClickListener {
            Toast.makeText(this, "Botão de voltar clicado", Toast.LENGTH_SHORT).show()
            finish()
        }
        binding.buttonUpdateTechnician.setOnClickListener {
            updateOnModel()
            showEditConfirmationDialog()
        }
        binding.buttonDeleteTechnician.setOnClickListener {
            showEditDeleteConfirmationDialog()
        }
    }

    private fun updateOnModel() {
        val editedTechnician = Technician(
            id = technicianId!!,
            name = binding.editTextName.text.toString(),
            email = binding.editTextEmail.text.toString(),
            phoneNumber = binding.editTextPhone.text.toString(),
            documentNumber = binding.editTextDocument.text.toString(),
            description = binding.editTextDescription.text.toString()
        )
        viewModel.setTechnician(editedTechnician)
    }

    private fun fillEditTexts(technician: Technician) {
        binding.editTextName.setText(technician.name)
        binding.editTextDocument.setText(technician.documentNumber)
        binding.editTextEmail.setText(technician.email)
        binding.editTextPhone.setText(technician.phoneNumber)
        binding.editTextDescription.setText(technician.description)
    }

    private fun showEditConfirmationDialog() {
        AlertDialog.Builder(this)
            .setTitle("Confirmar edição")
            .setMessage("Tem certeza de que deseja salvar as alterações deste cliente?")
            .setPositiveButton("Sim") { _, _ ->
                viewModel.editClient()
                viewModel.updateLiveData()
                finish()
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun showEditDeleteConfirmationDialog() {
        AlertDialog.Builder(this)
            .setTitle("Confirmar exclusão")
            .setMessage("Tem certeza de que deseja excluir este cliente?")
            .setPositiveButton("Sim") { _, _ ->
                viewModel.deleteTechnician()
                finish()
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }
}