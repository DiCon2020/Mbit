package com.david0926.mbit.ui.main.article

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.david0926.mbit.R
import com.david0926.mbit.data.UserModel
import com.david0926.mbit.data.post.Post
import com.david0926.mbit.data.post.PostVoteRequest
import com.david0926.mbit.databinding.ActivityArticleBinding
import com.david0926.mbit.network.post.PostManager
import com.david0926.mbit.ui.dialog.WorkingDialog
import com.david0926.mbit.ui.main.comment.CommentBottomSheet
import com.david0926.mbit.util.UserCache

class ArticleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityArticleBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_article)
        binding.lifecycleOwner = this

        val post = intent.getSerializableExtra("post") as Post
        binding.post = post
        binding.user = intent.getSerializableExtra("user") as UserModel

        binding.btnRowPostComment.setOnClickListener {
            val commentSheet = CommentBottomSheet(post._id, onDismiss = { finish() })
            commentSheet.show(supportFragmentManager, commentSheet.tag)
        }

        binding.btnRowPostLike.setOnClickListener { v ->
            val postManager = PostManager()
            postManager.votePost(UserCache.getToken(this), PostVoteRequest(post._id),
                onResponse = {
                    finish()
                },
                onFailure = {
                    it.printStackTrace()
                    Toast.makeText(this, "공감 전송에 실패했습니다.", Toast.LENGTH_SHORT).show()
                })
        }

        binding.btnRowPostDelete.setOnClickListener {
            WorkingDialog.working(this)
        }

        binding.btnArticleBack.setOnClickListener { onBackPressed() }
    }
}