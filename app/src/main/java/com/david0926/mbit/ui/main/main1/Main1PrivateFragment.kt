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
import androidx.recyclerview.widget.RecyclerView
import com.david0926.mbit.R
import com.david0926.mbit.databinding.FragmentMain1PrivateBinding
import com.david0926.mbit.ui.dialog.WorkingDialog
import com.david0926.mbit.ui.main.article.ArticleActivity
import com.david0926.mbit.ui.main.article.ArticleUploadActivity
import com.david0926.mbit.ui.main.comment.CommentBottomSheet
import com.david0926.mbit.util.UserCache

class Main1PrivateFragment : Fragment() {

    lateinit var viewModel: Main1ViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentMain1PrivateBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_main_1_private, container, false
        )
        binding.lifecycleOwner = requireActivity()

        viewModel = ViewModelProvider(requireActivity()).get(Main1ViewModel::class.java)
        binding.viewModel = viewModel

        binding.recyclerMain1Private.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        val adapter = Main1RecyclerAdapter(UserCache.getUser(requireContext()))
        adapter.onItemClick = {
            val articleIntent = Intent(requireContext(), ArticleActivity::class.java)
            articleIntent.putExtra("post", viewModel.privatePostList[it])
            articleIntent.putExtra("user", UserCache.getUser(requireContext()))
            startActivity(articleIntent)
        }

        binding.recyclerMain1Private.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lm = (recyclerView.layoutManager as LinearLayoutManager?)
                val visibleItemCount = lm!!.childCount
                val totalItemCount = lm.itemCount
                val firstVisibleItemPosition = lm.findFirstVisibleItemPosition()

                if ((visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0)) {

                    viewModel.nextPrivatePage(
                        UserCache.getToken(requireContext()),
                        UserCache.getUser(requireContext()).personalityType
                    ) {
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        })

        adapter.onCommentClick = {
            val commentSheet = CommentBottomSheet(viewModel.privatePostList[it]._id)
            //val bottomSheetBehavior = BottomSheetBehavior.from(commentSheet.)
            commentSheet.show(requireActivity().supportFragmentManager, commentSheet.tag)
        }

        adapter.onLikeClick = {
            WorkingDialog.working(requireActivity())
        }

        adapter.onDeleteClick = {
            WorkingDialog.working(requireActivity())
        }

        binding.recyclerMain1Private.adapter = adapter

        binding.btnMain1PrivateWrite.setOnClickListener {
            val uploadIntent = Intent(requireContext(), ArticleUploadActivity::class.java)
            uploadIntent.putExtra("private", true)
            startActivity(uploadIntent)
        }

        binding.swipeMain1Private.setOnRefreshListener { onResume() }
        viewModel.isPrivateLoaded.observe(viewLifecycleOwner) { isLoaded ->
            binding.swipeMain1Private.isRefreshing = !isLoaded
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.hasPrivateNext = true
        viewModel.privatePage = 0
        viewModel.getPrivatePostFromRepo(
            UserCache.getToken(requireContext()),
            UserCache.getUser(requireContext()).personalityType, 0
        )
    }
}