package com.david0926.mbit.ui.main

import android.widget.FrameLayout
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    val fragments = ObservableArrayList<Fragment>()
    val page = MutableLiveData(0)

    companion object {
        @BindingAdapter("bindFragment")
        @JvmStatic
        fun bindFragment(frame: FrameLayout, frag: Fragment?) {
            val activity = frame.context as FragmentActivity
            val transaction = activity.supportFragmentManager.beginTransaction()
            transaction.replace(frame.id, frag!!)
            transaction.commit()
        }
    }
}