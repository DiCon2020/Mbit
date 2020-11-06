package com.david0926.mbit.ui.main.main1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import com.david0926.mbit.data.UserModel
import com.david0926.mbit.data.post.Post
import com.david0926.mbit.databinding.RowPostBinding

class Main1RecyclerAdapter(userModel: UserModel) :
    RecyclerView.Adapter<Main1RecyclerAdapter.ChatHolder>() {

    class ChatHolder(var binding: RowPostBinding) : RecyclerView.ViewHolder(binding.root)

    private var posts: List<Post> = ArrayList()
    private var user = userModel

    var onItemClick: (position: Int) -> Unit = {}
    var onDeleteClick: (position: Int) -> Unit = {}
    var onCommentClick: (position: Int) -> Unit = {}
    var onLikeClick: (position: Int) -> Unit = {}

    fun setPosts(posts: ObservableArrayList<Post>) {
        this.posts = posts
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatHolder {
        return ChatHolder(
            RowPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ChatHolder, position: Int) {
        holder.binding.root.setOnClickListener { onItemClick.invoke(position) }
        holder.binding.btnRowPostLike.setOnClickListener { onLikeClick.invoke(position) }
        holder.binding.btnRowPostComment.setOnClickListener { onCommentClick.invoke(position) }
        holder.binding.btnRowPostDelete.setOnClickListener { onDeleteClick.invoke(position) }

        holder.binding.post = posts[position]
        holder.binding.user = user
    }

    override fun getItemCount(): Int {
        return posts.size
    }

}