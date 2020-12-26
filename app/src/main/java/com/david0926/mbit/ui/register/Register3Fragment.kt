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
import com.david0926.mbit.data.UserModel
import com.david0926.mbit.databinding.FragmentRegister3Binding
import com.david0926.mbit.ui.personality.PersonalityInputActivity
import com.david0926.mbit.ui.personality.PersonalityTestActivity

class Register3Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentRegister3Binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_register_3, container, false
        )
        binding.lifecycleOwner = requireActivity()

        val viewModel = ViewModelProvider(requireActivity()).get(RegisterViewModel::class.java)
        binding.viewModel = viewModel

        binding.btnRegister3Input.setOnClickListener {
            startActivityWithUser(
                Intent(requireContext(), PersonalityInputActivity::class.java),
                viewModel.token.value!!,
                viewModel.user.value!!
            )
        }

        binding.btnRegister3Survey.setOnClickListener {
            startActivityWithUser(
                Intent(requireContext(), PersonalityTestActivity::class.java),
                viewModel.token.value!!,
                viewModel.user.value!!
            )
        }

        return binding.root
    }

    private fun startActivityWithUser(intent: Intent, token: String, user: UserModel) {
        val bundle = Bundle()

        bundle.putString("token", token)
        bundle.putSerializable("user", user)
        intent.putExtras(bundle)

        startActivity(intent)
        requireActivity().finish()
    }
}