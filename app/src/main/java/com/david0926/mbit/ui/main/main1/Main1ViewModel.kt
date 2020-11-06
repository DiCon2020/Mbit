package com.david0926.mbit.ui.main.main1

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.david0926.mbit.data.post.Post
import com.david0926.mbit.data.post.PostGetRequest
import com.david0926.mbit.network.post.PostManager
import com.google.gson.Gson

class Main1ViewModel : ViewModel() {

    //fragment
    val fragments = ObservableArrayList<Fragment>()

    //private
    val privatePostList = ObservableArrayList<Post>()

    fun getPrivatePostFromRepo(token: String, personality: String) {
        val postManager = PostManager()
        postManager.getPosts(
            token,
            PostGetRequest(0, 0, personality),
            onResponse = { response, posts ->
                if (response.status != 200 || posts == null) {
                    Log.d("baam", "getPostFromRepo: " + response.message)
                    return@getPosts
                }
                privatePostList.clear()
                privatePostList.addAll(posts)
                Log.d("baam", "getPostFromRepo: " + Gson().toJson(posts))
            },
            onFailure = {
                it.printStackTrace()
            })
    }

    //public
    val publicPostList = ObservableArrayList<Post>()

    fun getPublicPostFromRepo(token: String) {
        val postManager = PostManager()
        postManager.getPosts(
            token,
            PostGetRequest(0, 0, ""),
            onResponse = { response, posts ->
                if (response.status != 200 || posts == null) {
                    Log.d("baam", "getPostFromRepo: " + response.message)
                    return@getPosts
                }
                publicPostList.clear()
                publicPostList.addAll(posts)
                Log.d("baam", "getPostFromRepo: " + Gson().toJson(posts))
            },
            onFailure = {
                it.printStackTrace()
            })
    }

    companion object {
        @JvmStatic
        @BindingAdapter("bindPosts")
        fun bindPosts(r: RecyclerView, posts: ObservableArrayList<Post>?) {
            val adapter: Main1RecyclerAdapter? = r.adapter as Main1RecyclerAdapter?
            if (adapter == null || posts == null) return
            adapter.setPosts(posts)
        }
    }

}