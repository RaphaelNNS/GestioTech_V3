package com.example.gestiotech_v3.presentation.Fragment

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gestiotech_v3.R
import com.example.gestiotech_v3.databinding.FragmentClientListBinding
import com.example.gestiotech_v3.model.entities.Client
import com.example.gestiotech_v3.presentation.ViewModel.ClientListViewModel
import com.example.gestiotech_v3.presentation.ViewModel.screenState.ClientListScreenState
import com.example.gestiotech_v3.presentation.ViewModel.screenState.displayState.DisplayState
import com.example.gestiotech_v3.presentation.adapter.ClientListAdapter

class ClientListFragment : Fragment() {

    private var numero: Int = 0
    private var texto: String = ""
    private lateinit var binding: FragmentClientListBinding
    private lateinit var recycler: RecyclerView

    companion object {
        fun newInstance() = ClientListFragment()
    }

    private val viewModel: ClientListViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentClientListBinding.inflate(inflater)
        setupRecyclerView()
        showLoading(true)
        val screenStateLiveData = viewModel.screenStateLiveData
        setupObserver(screenStateLiveData)
        binding.floatingActionButton.setOnClickListener{
            callAddClientFragment()
        }


        return binding.root
    }

    fun callAddClientFragment(){
        val fragmentManager = requireActivity().supportFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.fragment_home, AddClientFragment()).commit()
    }

    private fun setupObserver(screenStateLiveData: MutableLiveData<ClientListScreenState>) {
        screenStateLiveData.observe(viewLifecycleOwner) { screenState ->
            when (val displayState = screenState.clientlistDisplayState) {
                is DisplayState.Sucess -> {
                    showLoading(false)
                    showList(displayState.clientList)
                }

                is DisplayState.Error -> {
                    showLoading(false)
                    showError(displayState.exception.message.toString())
                }

                is DisplayState.Loading -> {
                    showLoading(true)
                }

                null -> TODO()
            }
        }
    }

    fun showError(s: String) {
        var alert = AlertDialog.Builder(requireContext())
        alert.setTitle("ERRO")
        alert.setMessage("Voce esta tentando efetuar uma operação não permitida.\n$s")
        alert.setNeutralButton("Voltar"){_,_ ->

        }
        alert.create().show()
    }
    private fun setupRecyclerView(){
        recycler = binding.recyclerViewClients
        recycler.layoutManager = LinearLayoutManager(requireContext())
    }

    fun showList(clients: List<Client>){
        recycler.adapter = ClientListAdapter(clients)
    }

    private fun showLoading(show: Boolean) {
        requireActivity().runOnUiThread {
            if (show){
                binding.progressBar.visibility = View.VISIBLE

            }else binding.progressBar.visibility = View.GONE

        }
    }
}