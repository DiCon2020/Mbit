package com.david0926.mbit.ui.main.comment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import com.david0926.mbit.data.comment.Comment
import com.david0926.mbit.databinding.RowCommentBinding

class CommentRecyclerAdapter : RecyclerView.Adapter<CommentRecyclerAdapter.ChatHolder>() {

    class ChatHolder(var binding: RowCommentBinding) : RecyclerView.ViewHolder(binding.root)

    private var comments: List<Comment> = ArrayList()

    fun setComments(comments: ObservableArrayList<Comment>) {
        this.comments = comments
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatHolder {
        return ChatHolder(
            RowCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ChatHolder, position: Int) {
        holder.binding.comment = comments[position]
    }

    override fun getItemCount(): Int {
        return comments.size
    }

}