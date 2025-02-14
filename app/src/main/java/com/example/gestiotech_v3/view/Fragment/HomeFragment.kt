package com.example.gestiotech_v3.view.Fragment

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.gestiotech_v3.R
import com.example.gestiotech_v3.view.ViewModel.HomeViewModel

class HomeFragment : Fragment() {

    private var numero: Int = 0
    private var texto: String = ""

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
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        var btn = view.findViewById<Button>(R.id.btnClick)
        btn.text = texto
        return view
    }
}