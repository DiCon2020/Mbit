package com.david0926.mbit.ui.main.main1

import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.david0926.mbit.data.post.Post
import com.david0926.mbit.data.post.PostGetRequest
import com.david0926.mbit.network.post.PostManager

class Main1ViewModel : ViewModel() {

    //fragment
    val fragments = ObservableArrayList<Fragment>()
    val COUNT_POST_PAGE = 5 // 한 페이지당 N개

    var hasPublicNext = true
    var hasPrivateNext = true

    var publicPage = 0
    var privatePage = 0

    //private
    val privatePostList = ObservableArrayList<Post>()
    val isPrivateLoaded = MutableLiveData(false)

    fun getPrivatePostFromRepo(token: String, personality: String, page: Int) {
        isPrivateLoaded.value = false
        val postManager = PostManager()
        postManager.getPosts(
            token,
            PostGetRequest(page, COUNT_POST_PAGE, personality),
            onResponse = { response, posts ->
                isPrivateLoaded.value = true
                if (response.status != 200 || posts == null) {
                    hasPrivateNext = false
                    return@getPosts
                }
                if (page == 0)
                    privatePostList.clear()
                if (posts.size < COUNT_POST_PAGE)
                    hasPrivateNext = false
                privatePostList.addAll(posts)
            },
            onFailure = {
                isPrivateLoaded.value = true
                it.printStackTrace()
            })
    }

    //public
    val publicPostList = ObservableArrayList<Post>()
    val isPublicLoaded = MutableLiveData(false)

    fun getPublicPostFromRepo(token: String, page: Int) {
        isPublicLoaded.value = false
        val postManager = PostManager()
        postManager.getPosts(
            token,
            PostGetRequest(page, COUNT_POST_PAGE, ""),
            onResponse = { response, posts ->
                isPublicLoaded.value = true
                if (response.status != 200 || posts == null) {
                    hasPublicNext = false
                    return@getPosts
                }
                if (page == 0)
                    publicPostList.clear()
                if (posts.size != COUNT_POST_PAGE)
                    hasPublicNext = false
                publicPostList.addAll(posts)
            },
            onFailure = {
                isPublicLoaded.value = true
                it.printStackTrace()
            })
    }

    fun nextPublicPage(token: String, finish: () -> Unit) {
        if (hasPublicNext) {
            getPublicPostFromRepo(token, ++publicPage)
            if (hasPublicNext)
                finish()
        }
    }

    fun nextPrivatePage(token: String, personality: String, finish: () -> Unit) {
        if (hasPrivateNext) {
            getPrivatePostFromRepo(token, personality, ++privatePage)
            if (hasPrivateNext)
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