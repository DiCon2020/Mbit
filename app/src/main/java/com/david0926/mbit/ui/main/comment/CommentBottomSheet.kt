package com.david0926.mbit.ui.main.comment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.david0926.mbit.R
import com.david0926.mbit.data.comment.CommentGetRequest
import com.david0926.mbit.databinding.BottomSheetCommentBinding
import com.david0926.mbit.network.comment.CommentManager
import com.david0926.mbit.util.UserCache
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CommentBottomSheet(private val postId: String) : BottomSheetDialogFragment() {

    lateinit var viewModel: CommentBottomSheetViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: BottomSheetCommentBinding =
            DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_comment, container, false)
        binding.lifecycleOwner = this

        viewModel =
            ViewModelProvider(requireActivity()).get(CommentBottomSheetViewModel::class.java)
        binding.viewModel = viewModel

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = CommentRecyclerAdapter()

        val commentManger = CommentManager()
        commentManger.getComments(
            UserCache.getToken(requireContext()),
            CommentGetRequest(postId),
            onResponse = { response, data ->
                if (response.status != 200) {
                    Toast.makeText(requireContext(), response.message, Toast.LENGTH_SHORT).show()
                    return@getComments
                }
                viewModel.commentList.clear()
                viewModel.commentList.addAll(data!!)
            },
            onFailure = {
                Toast.makeText(requireContext(), "댓글을 불러오는 데 실패했습니다.", Toast.LENGTH_SHORT).show()
                it.printStackTrace()
            })

        binding.btnCommentSend.setOnClickListener {
            viewModel.sendComment(UserCache.getToken(requireContext()), postId)
        }

        return binding.root
    }

}