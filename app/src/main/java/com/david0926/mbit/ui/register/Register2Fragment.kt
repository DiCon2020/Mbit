package com.david0926.mbit.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.david0926.mbit.R
import com.david0926.mbit.databinding.FragmentRegister2Binding

class Register2Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentRegister2Binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_register_2, container, false
        )
        binding.lifecycleOwner = requireActivity()

        val viewModel = ViewModelProvider(requireActivity()).get(RegisterViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.onNextClick = { viewModel.nextPage() }

        binding.btnRegister2Next.setOnClickListener { viewModel.onNextClick.invoke() }

        return binding.root
    }
}