package com.example.gestiotech_v3.presentation.Fragment

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.gestiotech_v3.databinding.FragmentHomeBinding
import com.example.gestiotech_v3.model.repository.FirebaseHandler
import com.example.gestiotech_v3.presentation.ViewModel.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class   HomeFragment : Fragment() {

    private var numero: Int = 0
    private var texto: String = ""
    private lateinit var binding: FragmentHomeBinding

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel: HomeViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel

        val valor = arguments?.getInt("testeDeInt")
        if (valor != null){
            numero = valor
        }
        texto = arguments?.getString("teste").toString()


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        var dbHandler = FirebaseHandler()
        binding = FragmentHomeBinding.inflate(inflater)
        binding.btnClick.setOnClickListener{
            var name = binding.edName.text.toString()
            var password =  binding.edPassword.text.toString()
            var cpf = binding.edCPF.text.toString()
            lifecycleScope.launch{
                withContext(Dispatchers.Main){
                }
            }
        }



        return binding.root
    }


}
