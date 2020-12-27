package com.david0926.mbit.ui.notification

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.david0926.mbit.R
import com.david0926.mbit.databinding.ActivityNotiBinding
import com.david0926.mbit.util.UserCache

class NotiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityNotiBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_noti)
        binding.lifecycleOwner = this

        val viewModel = ViewModelProvider(this).get(NotiActivityViewModel::class.java)
        binding.viewModel = viewModel

        binding.recyclerNoti.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val adapter = NotiRecyclerAdapter()
        binding.recyclerNoti.adapter = adapter

        binding.btnNotiBack.setOnClickListener { finish() }

        val notiList = UserCache.getUser(this).notificationList

        notiList.reverse().run { viewModel.notiList.addAll(notiList) }

    }
}