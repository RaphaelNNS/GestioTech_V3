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
import com.example.gestiotech_v3.databinding.FragmentTecListBinding
import com.example.gestiotech_v3.model.entities.Technician
import com.example.gestiotech_v3.presentation.ViewModel.TecListViewModel
import com.example.gestiotech_v3.presentation.ViewModel.screenState.TecListScreenState
import com.example.gestiotech_v3.presentation.ViewModel.screenState.displayState.TechnicianDisplayState
import com.example.gestiotech_v3.presentation.adapter.TechnicianListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TecListFragment : Fragment() {

    private lateinit var binding: FragmentTecListBinding
    private lateinit var recycler: RecyclerView
    private val viewModel: TecListViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTecListBinding.inflate(inflater)
        setupRecyclerView()
        showLoading(true)
        val screenStateLiveData = viewModel.screenStateLiveData
        setupObserver(screenStateLiveData)
        binding.floatingActionButton.setOnClickListener{
            callAddTechnicianFragment()
        }
        binding.floatingButtonUpdateList.setOnClickListener{
            viewModel.getTechnicians()
        }


        return binding.root
    }
    private fun setupObserver(screenStateLiveData: MutableLiveData<TecListScreenState>) {
        screenStateLiveData.observe(viewLifecycleOwner) { screenState ->
            when (val displayState = screenState.displayState) {
                is TechnicianDisplayState.Success -> {
                    showLoading(false)
                    showList(displayState.technicianList)
                }

                is TechnicianDisplayState.Error -> {
                    showLoading(false)
                    showError(displayState.exception.message.toString())
                }

                is TechnicianDisplayState.Loading -> {
                    showLoading(true)
                }

                null -> TODO()
            }
        }
    }


    fun callAddTechnicianFragment(){
        val fragmentManager = requireActivity().supportFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.fragment_home, AddTechnicianFragment()).commit()
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
        recycler = binding.recyclerViewTechnicians
        recycler.layoutManager = LinearLayoutManager(requireContext())
    }

    fun showList(tecList: List<Technician>){

        recycler.adapter = TechnicianListAdapter(tecList)
    }

    private fun showLoading(show: Boolean) {
        requireActivity().runOnUiThread {
            if (show){
                binding.progressBar.visibility = View.VISIBLE

            }else binding.progressBar.visibility = View.GONE

        }
    }
}