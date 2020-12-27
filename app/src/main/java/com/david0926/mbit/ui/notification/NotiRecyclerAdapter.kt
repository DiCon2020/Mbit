package com.david0926.mbit.ui.notification

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.david0926.mbit.data.notification.Notification
import com.david0926.mbit.databinding.RowNotiBinding

class NotiRecyclerAdapter : RecyclerView.Adapter<NotiRecyclerAdapter.NotiRecyclerHolder>() {

    class NotiRecyclerHolder(var binding: RowNotiBinding) : RecyclerView.ViewHolder(binding.root)

    private var notis = ArrayList<Notification>()

    fun setNotis(notis: ArrayList<Notification>) {
        this.notis = notis
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotiRecyclerHolder {
        return NotiRecyclerHolder(
            RowNotiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: NotiRecyclerHolder, position: Int) {
        holder.binding.noti = notis[position]
    }

    override fun getItemCount(): Int {
        return notis.size
    }
}