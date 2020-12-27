package com.david0926.mbit.ui.main.article

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.david0926.mbit.R
import com.david0926.mbit.data.UserModel
import com.david0926.mbit.data.post.Post
import com.david0926.mbit.data.post.PostDeleteRequest
import com.david0926.mbit.data.post.PostVoteRequest
import com.david0926.mbit.databinding.ActivityArticleBinding
import com.david0926.mbit.network.post.PostManager
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
            val commentSheet = CommentBottomSheet(post._id, false, onDismiss = { finish() })
            commentSheet.show(supportFragmentManager, commentSheet.tag)
        }

        binding.btnRowPostLike.setOnClickListener {
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
            val builder = AlertDialog.Builder(this, R.style.AlertDialogTheme)
            builder.setMessage("이 게시물을 삭제할까요?")
            builder.setPositiveButton("삭제") { _, _ ->
                val postManager = PostManager()
                postManager.deletePost(
                    UserCache.getToken(this),
                    PostDeleteRequest(post._id),
                    onResponse = {
                        finish()
                    },
                    onFailure = {
                        it.printStackTrace()
                        Toast.makeText(this, "게시물 삭제에 실패했습니다.", Toast.LENGTH_SHORT).show()
                    })
            }.setNegativeButton("취소") { _, _ -> }.show()
        }

        binding.btnArticleBack.setOnClickListener { onBackPressed() }
    }
}