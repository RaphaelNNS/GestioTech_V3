package com.example.gestiotech_v3.view.Fragment

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import com.example.gestiotech_v3.R
import com.example.gestiotech_v3.databinding.ActivityHomeBinding
import com.example.gestiotech_v3.databinding.FragmentHomeBinding
import com.example.gestiotech_v3.databinding.FragmentSubjectBinding
import com.example.gestiotech_v3.model.auth.FirebaseHandler
import com.example.gestiotech_v3.view.ViewModel.HomeViewModel
import com.example.gestiotech_v3.view.adapter.ClientListAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {

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
                    binding.textView.text = dbHandler.addUser(name, password, cpf)
                }
            }
        }



        return binding.root
    }


}
