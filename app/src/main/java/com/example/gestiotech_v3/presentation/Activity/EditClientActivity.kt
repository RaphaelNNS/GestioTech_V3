package com.example.gestiotech_v3.presentation.Activity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.gestiotech_v3.data.repository.ClientRepositoryFirestore
import com.example.gestiotech_v3.databinding.ActivityEditClientBinding
import com.example.gestiotech_v3.model.entities.Client
import com.example.gestiotech_v3.presentation.ViewModel.EditClientViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EditClientActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditClientBinding
    private lateinit var client: Client
    private val viewModel: EditClientViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditClientBinding.inflate(layoutInflater)
        setContentView(binding.root)

        client = intent.getParcelableExtra("client") ?: Client()
        viewModel.setClient(client)


        fillEditTexts()
        setupButtons()
        setupObserver()

    }

    private fun setupObserver() {
        viewModel.screenStateLiveData.observe(this) { screenState ->
            if (screenState.isClientDone) finish()
            binding.textErrorMessage.text = screenState.errorMessage
            showLoading(screenState.isLoading)
            freezeInterface(screenState.isLoading)
        }
    }

    private fun showLoading(visibility: Boolean) {
        binding.progressBar.visibility = if (visibility) View.VISIBLE else View.GONE
    }

    private fun freezeInterface(visibility: Boolean) {
        binding.loadingOverlay.visibility = if (visibility) View.VISIBLE else View.GONE
    }

    private fun setupButtons() {
        binding.buttonBack.setOnClickListener { finish() }
        binding.buttonUpdateClient.setOnClickListener {
            updateOnModel()
            showEditConfirmationDialog()
        }
        binding.buttonDeleteClient.setOnClickListener {
            showEditDeleteConfirmationDialog()
        }
    }



    private fun updateOnModel() {
        val editedClient = Client(
            id = client.id,
            name = binding.editTextName.text.toString(),
            documentNumber = binding.editTextDocument.text.toString(),
            address = binding.editTextAddress.text.toString(),
            phoneNumber = binding.editTextPhone.text.toString(),
            description = binding.editTextDescription.text.toString()
        )
        viewModel.setClient(editedClient)
    }

    private fun fillEditTexts() {
        binding.editTextName.setText(client.name)
        binding.editTextDocument.setText(client.documentNumber)
        binding.editTextAddress.setText(client.address)
        binding.editTextPhone.setText(client.phoneNumber)
        binding.editTextDescription.setText(client.description)
    }

    private fun showEditConfirmationDialog() {
        AlertDialog.Builder(this)
            .setTitle("Confirmar edição")
            .setMessage("Tem certeza de que deseja salvar as alterações deste cliente?")
            .setPositiveButton("Sim") { _, _ ->
                updateOnModel()
                viewModel.editClient()
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
                updateOnModel()
                viewModel.deleteClient()
                finish()
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }




}

