package com.david0926.mbit.ui.main.main1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.david0926.mbit.data.UserModel
import com.david0926.mbit.data.post.Post
import com.david0926.mbit.databinding.RowPostBinding

class Main1RecyclerAdapter(userModel: UserModel) :
    RecyclerView.Adapter<Main1RecyclerAdapter.ChatHolder>() {

    class ChatHolder(var binding: RowPostBinding) : RecyclerView.ViewHolder(binding.root)

    private var posts = ArrayList<Post>()
    private var user = userModel

    var onItemClick: (position: Int) -> Unit = {}
    var onDeleteClick: (position: Int) -> Unit = {}
    var onCommentClick: (position: Int) -> Unit = {}
    var onLikeClick: (position: Int) -> Unit = {}

    fun setPosts(posts: ArrayList<Post>) {
        val diffCallBack = Main1RecyclerDiffCallBack(this.posts, posts)
        val diffResult = DiffUtil.calculateDiff(diffCallBack)

        this.posts.clear()
        this.posts.addAll(posts)

        diffResult.dispatchUpdatesTo(this)
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

    class Main1RecyclerDiffCallBack(
        private var oldList: List<Post>,
        private var newList: List<Post>
    ) :
        DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            if (oldListSize != newListSize || newList.isEmpty()) return false
            return oldList[oldItemPosition]._id == newList[newItemPosition]._id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            if (oldListSize != newListSize || newList.isEmpty()) return false
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

    }

}