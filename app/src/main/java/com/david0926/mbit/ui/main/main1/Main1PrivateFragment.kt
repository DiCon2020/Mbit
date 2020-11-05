package com.david0926.mbit.ui.main.main1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.david0926.mbit.R
import com.david0926.mbit.databinding.FragmentMain1Binding
import com.david0926.mbit.databinding.FragmentMain1PrivateBinding

class Main1PrivateFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentMain1PrivateBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_main_1_private, container, false
        )
        binding.lifecycleOwner = requireActivity()

        val viewModel = ViewModelProvider(requireActivity()).get(Main1ViewModel::class.java)
        binding.viewModel = viewModel

        return binding.root
    }
}