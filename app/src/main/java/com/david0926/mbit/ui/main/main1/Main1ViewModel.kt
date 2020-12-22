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
    val COUNT_POST_PAGE = 5; // 한 페이지당 N개

    var hasPublicNext = true;
    var hasPrivateNext = true;

    var PUBLIC_PAGE = 0;
    var PRIVATE_PAGE = 0;

    //private
    val privatePostList = ObservableArrayList<Post>()

    fun getPrivatePostFromRepo(token: String, personality: String, page: Int) {
        val postManager = PostManager()
        postManager.getPosts(
            token,
            PostGetRequest(page, COUNT_POST_PAGE, personality),
            onResponse = { response, posts ->
                if (response.status != 200 || posts == null) {
                    Log.d("baam", "getPostFromRepo: " + response.message)
                    hasPrivateNext = false
                    return@getPosts
                }
                if(page == 0)
                    privatePostList.clear()
                if(posts.size < COUNT_POST_PAGE)
                    hasPrivateNext=false;
                privatePostList.addAll(posts)
                Log.d("baam", "getPostFromRepo: " + Gson().toJson(posts))
            },
            onFailure = {
                it.printStackTrace()
            })
    }

    //public
    val publicPostList = ObservableArrayList<Post>()

    fun getPublicPostFromRepo(token: String, page: Int) {
        val postManager = PostManager()
        postManager.getPosts(
            token,
            PostGetRequest(page, COUNT_POST_PAGE, ""),
            onResponse = { response, posts ->
                if (response.status != 200 || posts == null) {
                    Log.d("baam", "getPostFromRepo: " + response.message)
                    hasPublicNext=false;
                    return@getPosts
                }
                if(page == 0)
                    publicPostList.clear()
                if(posts.size != COUNT_POST_PAGE)
                    hasPublicNext=false;
                publicPostList.addAll(posts)
                Log.d("baam", "getPostFromRepo: " + Gson().toJson(posts))
            },
            onFailure = {
                it.printStackTrace()
            })
    }

    fun nextPublicPage(token: String, finish: () -> Unit) {
        if(hasPublicNext) {
            getPublicPostFromRepo(token, ++PUBLIC_PAGE);
            if(hasPublicNext)
                finish()
        }
    }

    fun nextPrivatePage(token: String, personality: String, finish: () -> Unit ) {
        if(hasPrivateNext) {
            getPrivatePostFromRepo(token, personality, ++PRIVATE_PAGE);
            if(hasPrivateNext)
                finish()
        }
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