package com.david0926.mbit.ui.main.main3

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.david0926.mbit.R
import com.david0926.mbit.databinding.FragmentMain3Binding
import com.david0926.mbit.util.UserCache

class Main3Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentMain3Binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_main_3, container, false
        )
        binding.lifecycleOwner = requireActivity()

        val viewModel = ViewModelProvider(requireActivity()).get(Main3FragmentViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.getPersonality(UserCache.getToken(requireContext()))

        binding.btnMain3More.setOnClickListener {
            val intent = Intent(Intent.ACTION_WEB_SEARCH)
            intent.putExtra(
                SearchManager.QUERY,
                viewModel.personality.value!!.myInfo.personalityType
            )
            startActivity(intent)
        }

        return binding.root
    }
}