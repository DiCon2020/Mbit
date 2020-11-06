package com.david0926.mbit.ui.register

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.david0926.mbit.R
import com.david0926.mbit.databinding.FragmentRegister3Binding
import com.david0926.mbit.ui.personality.PersonalityInputActivity

class Register3Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentRegister3Binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_register_3, container, false
        )
        binding.lifecycleOwner = requireActivity()

        val viewModel = ViewModelProvider(requireActivity()).get(RegisterViewModel::class.java)
        binding.viewModel = viewModel

        binding.btnRegister3Input.setOnClickListener {
            val inputIntent = Intent(requireContext(), PersonalityInputActivity::class.java)
            val bundle = Bundle()

            bundle.putString("token", viewModel.token.value)
            bundle.putSerializable("user", viewModel.user.value)
            inputIntent.putExtras(bundle)

            startActivity(inputIntent)
            requireActivity().finish()
        }

        binding.btnRegister3Survey.setOnClickListener {
            // TODO: 2020/11/06 성격유형 설문 기능 추가
        }

        return binding.root
    }
}