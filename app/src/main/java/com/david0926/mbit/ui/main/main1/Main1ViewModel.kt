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
    val postList = ObservableArrayList<Post>()

    fun getPostFromRepo(token: String, personality: String) {
        val postManager = PostManager()
        postManager.getPosts(
            token,
            PostGetRequest(0, 0, personality),
            onResponse = { response, posts ->
                if (response.status != 200 || posts == null) {
                    Log.d("baam", "getPostFromRepo: "+response.message)
                    return@getPosts
                }
                postList.clear()
                postList.addAll(posts)
                Log.d("baam", "getPostFromRepo: "+Gson().toJson(posts))
            },
            onFailure = {
                it.printStackTrace()
            })
    }

    companion object {
        @JvmStatic
        @BindingAdapter("bindPosts")
        fun bindPosts(r: RecyclerView, posts: ObservableArrayList<Post>?) {
            val adapter: Main1PrivateRecyclerAdapter? = r.adapter as Main1PrivateRecyclerAdapter?
            if (adapter == null || posts == null) return
            adapter.setPosts(posts)
        }
    }

}