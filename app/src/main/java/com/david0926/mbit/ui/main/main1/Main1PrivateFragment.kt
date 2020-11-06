package com.david0926.mbit.ui.main.main1

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.david0926.mbit.R
import com.david0926.mbit.databinding.FragmentMain1PrivateBinding
import com.david0926.mbit.ui.main.article.ArticleUploadActivity
import com.david0926.mbit.util.UserCache

class Main1PrivateFragment : Fragment() {

    lateinit var viewModel: Main1ViewModel

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

        viewModel = ViewModelProvider(requireActivity()).get(Main1ViewModel::class.java)
        binding.viewModel = viewModel

        binding.recyclerMain1Private.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.recyclerMain1Private.adapter =
            Main1PrivateRecyclerAdapter(UserCache.getUser(requireContext()))

        binding.btnMain1PrivateWrite.setOnClickListener {
            val uploadIntent = Intent(requireContext(), ArticleUploadActivity::class.java)
            uploadIntent.putExtra("private", true)
            startActivity(uploadIntent)
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getPrivatePostFromRepo(
            UserCache.getToken(requireContext()),
            UserCache.getUser(requireContext()).personalityType
        )
    }
}