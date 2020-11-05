package com.david0926.mbit.ui.register

import android.net.Uri
import android.widget.FrameLayout
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.david0926.mbit.R
import com.google.android.material.tabs.TabLayout

class RegisterViewModel : ViewModel() {

    //activity
    val fragments = ObservableArrayList<Fragment>()
    val page = MutableLiveData(0)

    fun nextPage() {
        val currentPage = page.value!!
        if (currentPage < fragments.size - 2) page.value = currentPage + 1
    }

    fun previousPage() {
        val currentPage = page.value!!
        if (currentPage > 0) page.value = currentPage - 1
    }

    val errorMsg = MutableLiveData("")

    val nextEnabled = MutableLiveData(false)
    var onNextClick: () -> Unit = {}

    object RegisterViewModelBindingOptions {
        @BindingAdapter("bindFragment")
        @JvmStatic
        fun bindFragment(frame: FrameLayout, frag: Fragment?) {
            val activity = frame.context as FragmentActivity
            val transaction = activity.supportFragmentManager.beginTransaction()
            transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
            transaction.replace(frame.id, frag!!)
            transaction.commit()
        }

        @BindingAdapter("bindIndicatorIndex")
        @JvmStatic
        fun bindIndicatorIndex(t: TabLayout, i: Int) {
            val tab = t.getTabAt(i)
            tab?.select()
        }

        @BindingAdapter("bindTabClickable")
        @JvmStatic
        fun bindTabClickable(t: TabLayout, b: Boolean) {
            for (v in t.touchables) {
                v.isClickable = b
                v.isFocusable = b
            }
        }
    }

    //fragment 1
    val email = MutableLiveData("")
    val pw = MutableLiveData("")
    val pwCheck = MutableLiveData("")

    //fragment 2
    val profile = MutableLiveData<Uri>()
    val name = MutableLiveData("")
    val birth = MutableLiveData("")
    val policy = MutableLiveData(false)

    //fragment 3

}