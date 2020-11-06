package com.david0926.mbit.ui.main.article

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.david0926.mbit.R
import com.david0926.mbit.data.UserModel
import com.david0926.mbit.data.post.Post
import com.david0926.mbit.databinding.ActivityArticleBinding
import com.david0926.mbit.ui.dialog.WorkingDialog
import com.david0926.mbit.ui.main.comment.CommentBottomSheet

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
            val commentSheet = CommentBottomSheet(post._id)
            commentSheet.show(supportFragmentManager, commentSheet.tag)
        }

        binding.btnRowPostLike.setOnClickListener {
            WorkingDialog.working(this)
        }

        binding.btnRowPostDelete.setOnClickListener {
            WorkingDialog.working(this)
        }

        binding.btnArticleBack.setOnClickListener { onBackPressed() }
    }
}