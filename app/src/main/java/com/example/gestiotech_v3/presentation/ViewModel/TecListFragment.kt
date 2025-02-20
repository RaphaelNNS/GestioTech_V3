package com.example.gestiotech_v3.presentation.ViewModel

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gestiotech_v3.R
import com.example.gestiotech_v3.presentation.Fragment.TecListViewModel

class TecListFragment : Fragment() {

    companion object {
        fun newInstance() = TecListFragment()
    }

    private val viewModel: TecListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_tec_list, container, false)
    }
}