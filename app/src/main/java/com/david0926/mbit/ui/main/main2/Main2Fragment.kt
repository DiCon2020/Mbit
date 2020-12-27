package com.david0926.mbit.ui.main.main2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.david0926.mbit.R
import com.david0926.mbit.databinding.FragmentMain2Binding
import com.david0926.mbit.ui.main.comment.CommentBottomSheet
import com.david0926.mbit.util.UserCache

class Main2Fragment : Fragment() {

    lateinit var viewModel: Main2FragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentMain2Binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_main_2, container, false
        )
        binding.lifecycleOwner = requireActivity()

        viewModel = ViewModelProvider(requireActivity()).get(Main2FragmentViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.getLastTopic(UserCache.getToken(requireContext()))

        binding.btnMain2Q1.setOnClickListener {
            viewModel.voteTopic(UserCache.getToken(requireContext()), "first")
        }

        binding.btnMain2Q2.setOnClickListener {
            viewModel.voteTopic(UserCache.getToken(requireContext()), "second")
        }

        binding.btnMain2Comment.setOnClickListener {
            val commentSheet =
                CommentBottomSheet(viewModel.todayTopic.value!!._id, true, onDismiss = {})
            commentSheet.show(requireActivity().supportFragmentManager, commentSheet.tag)
        }

        return binding.root
    }


}