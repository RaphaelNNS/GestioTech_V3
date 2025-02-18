package com.example.gestiotech_v3.view.Fragment

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gestiotech_v3.R
import com.example.gestiotech_v3.databinding.FragmentSubjectBinding
import com.example.gestiotech_v3.model.auth.FirebaseHandler
import com.example.gestiotech_v3.view.ViewModel.SubjectViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SubjectFragment : Fragment() {

    private lateinit var binding: FragmentSubjectBinding

    companion object {
        fun newInstance() = SubjectFragment()
    }

    private val viewModel: SubjectViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSubjectBinding.inflate(layoutInflater)

        show()
        return binding.root
    }

    fun show(){
        var firebaseHandler = FirebaseHandler()

        var scope = CoroutineScope(Dispatchers.IO)
        scope.launch{
            withContext(Dispatchers.Main){
                binding.text.text = firebaseHandler.getClients().toString()
            }
        }

    }

}