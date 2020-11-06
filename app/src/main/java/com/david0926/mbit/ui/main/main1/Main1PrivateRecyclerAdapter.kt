package com.david0926.mbit.ui.main.main1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import com.david0926.mbit.data.UserModel
import com.david0926.mbit.data.post.Post
import com.david0926.mbit.databinding.RowPostBinding

class Main1PrivateRecyclerAdapter(userModel: UserModel) :
    RecyclerView.Adapter<Main1PrivateRecyclerAdapter.ChatHolder>() {

    class ChatHolder(var binding: RowPostBinding) : RecyclerView.ViewHolder(binding.root)

    private var posts: List<Post> = ArrayList()
    private var user = userModel

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
        holder.binding.post = posts[position]
        holder.binding.user = user
    }

    override fun getItemCount(): Int {
        return posts.size
    }

}