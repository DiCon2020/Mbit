package com.david0926.mbit.ui.main.main1

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.util.*

class Main1PagerAdapter(fragmentActivity: FragmentActivity, list: List<Fragment>?) :
    FragmentStateAdapter(fragmentActivity) {
    private val fragments = ArrayList<Fragment>()
    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    override fun getItemCount(): Int {
        return fragments.size
    }

    init {
        fragments.addAll(list!!)
    }
}
