package com.david0926.mbit.ui.main.main1

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.david0926.mbit.R
import com.david0926.mbit.databinding.FragmentMain1PublicBinding
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
    ): View {

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
            articleIntent.putExtra("post", viewModel.publicPostList[it])
            articleIntent.putExtra("user", UserCache.getUser(requireContext()))
            startActivity(articleIntent)
        }

        binding.recyclerMain1Public.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lm = (recyclerView.layoutManager as LinearLayoutManager?)
                val visibleItemCount = lm!!.childCount
                val totalItemCount = lm.itemCount
                val firstVisibleItemPosition = lm.findFirstVisibleItemPosition()

                if ((visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0)) {
                    //if (recyclerView.canScrollVertically(-1)) viewModel.isPublicBottom.value = true
                    UserCache.getToken(requireContext()).run {
                        viewModel.nextPublicPage(this) { }
                    }
                } //else viewModel.isPublicBottom.value = false
            }
        })

        adapter.onCommentClick = {
            val commentSheet =
                CommentBottomSheet(viewModel.publicPostList[it]._id, onDismiss = { onResume() })
            commentSheet.show(requireActivity().supportFragmentManager, commentSheet.tag)
        }

        adapter.onLikeClick = {
            viewModel.votePost(
                UserCache.getToken(requireContext()),
                viewModel.publicPostList[it]._id,
                null,
                failed = {
                    Toast.makeText(requireContext(), "공감 전송에 실패했습니다.", Toast.LENGTH_SHORT).show()
                })
        }

        adapter.onDeleteClick = {
            val builder = AlertDialog.Builder(requireContext(), R.style.AlertDialogTheme)
            builder.setMessage("이 게시물을 삭제할까요?")
            builder.setPositiveButton("삭제") { _, _ ->
                viewModel.deletePost(
                    UserCache.getToken(requireContext()),
                    viewModel.publicPostList[it]._id,
                    null,
                    failed = {
                        Toast.makeText(requireContext(), "게시물 삭제에 실패했습니다.", Toast.LENGTH_SHORT)
                            .show()
                    })
            }.setNegativeButton("취소") { _, _ -> }.show()
        }


        binding.recyclerMain1Public.adapter = adapter

        binding.btnMain1PublicWrite.setOnClickListener {
            val uploadIntent = Intent(requireContext(), ArticleUploadActivity::class.java)
            uploadIntent.putExtra("public", false)
            startActivity(uploadIntent)
        }

        binding.swipeMain1Public.setOnRefreshListener { onResume() }
        viewModel.isPublicLoaded.observe(viewLifecycleOwner) { isLoaded ->
            binding.swipeMain1Public.isRefreshing = !isLoaded
        }

        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (isEnabled && !binding.recyclerMain1Public.canScrollVertically(-1)) {
                        isEnabled = false
                        requireActivity().onBackPressed()
                    } else binding.recyclerMain1Public.smoothScrollToPosition(0)
                }
            })

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.hasPublicNext = true
        viewModel.publicPage = -1
        viewModel.nextPublicPage(UserCache.getToken(requireContext())) {

        }
    }
}