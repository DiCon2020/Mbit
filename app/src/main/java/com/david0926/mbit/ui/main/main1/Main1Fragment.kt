package com.david0926.mbit.ui.main.main1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.david0926.mbit.R
import com.david0926.mbit.databinding.FragmentMain1Binding
import com.david0926.mbit.util.UserCache
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class Main1Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentMain1Binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_main_1, container, false
        )
        binding.lifecycleOwner = requireActivity()

        val viewModel = ViewModelProvider(this).get(Main1ViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.fragments.addAll(listOf(Main1PrivateFragment(), Main1PublicFragment()))

        val adapter = Main1PagerAdapter(requireActivity(), viewModel.fragments)
        binding.pagerMain1.adapter = adapter

        TabLayoutMediator(
            binding.tabMain1,
            binding.pagerMain1
        ) { tab: TabLayout.Tab, position: Int ->
            tab.text = when (position) {
                0 -> UserCache.getUser(requireContext()).personalityType
                else -> "전체"
            }
        }.attach()

        binding.pagerMain1.isUserInputEnabled = false

        return binding.root
    }
}