package com.david0926.mbit.ui.notification

import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.david0926.mbit.data.notification.Notification
import com.david0926.mbit.data.post.Post
import com.david0926.mbit.ui.main.main1.Main1RecyclerAdapter

class NotiActivityViewModel : ViewModel() {

    val notiList = ObservableArrayList<Notification>()

    companion object{
        @JvmStatic
        @BindingAdapter("bindNotis")
        fun bindNotis(r: RecyclerView, notis: ObservableArrayList<Notification>?){
            val adapter: NotiRecyclerAdapter? = r.adapter as NotiRecyclerAdapter?
            if (adapter == null || notis == null) return
            adapter.setNotis(notis)
        }
    }
}