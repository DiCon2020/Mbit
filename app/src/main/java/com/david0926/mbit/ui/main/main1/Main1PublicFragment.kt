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
import com.david0926.mbit.databinding.FragmentMain1PublicBinding
import com.david0926.mbit.ui.dialog.WorkingDialog
import com.david0926.mbit.ui.main.article.ArticleActivity
import com.david0926.mbit.ui.main.article.ArticleUploadActivity
import com.david0926.mbit.ui.main.comment.CommentBottomSheet
import com.david0926.mbit.util.UserCache

class Main1PublicFragment : Fragment() {

    lateinit var viewModel: Main1ViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentMain1PublicBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_main_1_public, container, false
        )
        binding.lifecycleOwner = requireActivity()

        viewModel = ViewModelProvider(requireActivity()).get(Main1ViewModel::class.java)
        binding.viewModel = viewModel

        binding.recyclerMain1Public.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        val adapter = Main1RecyclerAdapter(UserCache.getUser(requireContext()))
        adapter.onItemClick = {
            val articleIntent = Intent(requireContext(), ArticleActivity::class.java)
            articleIntent.putExtra("post", viewModel.privatePostList[it])
            articleIntent.putExtra("user", UserCache.getUser(requireContext()))
            startActivity(articleIntent)
        }

        adapter.onCommentClick = {
            val commentSheet = CommentBottomSheet(viewModel.privatePostList[it]._id)
            commentSheet.show(requireActivity().supportFragmentManager, commentSheet.tag)
        }

        adapter.onLikeClick = {
            WorkingDialog.working(requireActivity())
        }
        adapter.onDeleteClick = {
            WorkingDialog.working(requireActivity())
        }


        binding.recyclerMain1Public.adapter = adapter

        binding.btnMain1PublicWrite.setOnClickListener {
            val uploadIntent = Intent(requireContext(), ArticleUploadActivity::class.java)
            uploadIntent.putExtra("private", false)
            startActivity(uploadIntent)
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getPublicPostFromRepo(UserCache.getToken(requireContext()))
    }
}