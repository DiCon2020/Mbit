package com.david0926.mbit.ui.main.main4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.david0926.mbit.R
import com.david0926.mbit.databinding.FragmentMain4Binding

class Main4Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentMain4Binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_main_4, container, false
        )
        binding.lifecycleOwner = requireActivity()

        val viewModel = ViewModelProvider(requireActivity()).get(Main4FragmentViewModel::class.java)
        binding.viewModel = viewModel

        return binding.root
    }
}