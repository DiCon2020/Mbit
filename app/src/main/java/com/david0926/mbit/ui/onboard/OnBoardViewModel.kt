package com.david0926.mbit.ui.onboard

import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.david0926.mbit.R
import com.david0926.mbit.ui.onboard.OnBoardPagerFragment.Companion.newInstance
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.util.*

class OnBoardViewModel : ViewModel() {
    var fragments: MutableList<Fragment> = ArrayList()
    var currentPage = MutableLiveData(0)
    var pageChangeRequest = MutableLiveData(0)
    val isFirstPage: Boolean
        get() = currentPage.value == 0

    fun previousPage() {
        var value = currentPage.value!!
        if (value > 0) {
            value--
            currentPage.value = value
            pageChangeRequest.value = value
        }
    }

    var pagerCallback: OnPageChangeCallback = object : OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            currentPage.value = position
        }
    }
}