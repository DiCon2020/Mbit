package com.david0926.mbit.ui.main.comment

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.david0926.mbit.data.comment.Comment
import com.david0926.mbit.data.comment.CommentAddRequest
import com.david0926.mbit.network.comment.CommentManager

class CommentBottomSheetViewModel : ViewModel() {

    val commentList = ObservableArrayList<Comment>()
    val text = MutableLiveData("")

    fun sendComment(token: String, postId: String, finish: () -> Unit) {
        val sendText = text.value
        text.value = ""
        val commentManager = CommentManager()
        commentManager.addComment(token, CommentAddRequest(postId, "", sendText!!),
            onResponse = { response, data ->
                if (response.status != 200) {
                    return@addComment
                }
                commentList.clear()
                commentList.addAll(data!!)
                finish.invoke()
            }, onFailure = {
                it.printStackTrace()
            })
    }

    companion object {
        @JvmStatic
        @BindingAdapter("bindComments")
        fun bindComments(r: RecyclerView, comments: ObservableArrayList<Comment>?) {
            val adapter: CommentRecyclerAdapter? = r.adapter as CommentRecyclerAdapter?
            if (adapter == null || comments == null) return
            adapter.setComments(comments)
        }
    }

}