package com.david0926.mbit.util

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.view.View
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.david0926.mbit.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

object BindingOptions {

    @JvmStatic
    @BindingConversion
    fun convertBooleanToVisibility(visible: Boolean): Int {
        return if (visible) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("bindInvisibility")
    fun bindInvisibility(v: View, b: Boolean) {
        v.visibility = if (b) View.VISIBLE else View.INVISIBLE
    }

    @BindingAdapter("bindPagerCurrentItem")
    @JvmStatic
    fun bindPagerCurrentItem(pager: ViewPager2, position: Int) {
        pager.currentItem = position
    }

    @BindingAdapter("bindPagerCallback")
    @JvmStatic
    fun bindPagerCallback(pager: ViewPager2, c: ViewPager2.OnPageChangeCallback?) {
        pager.registerOnPageChangeCallback(c!!)
    }

    @BindingAdapter("bindTabMediator", "bindTabClickable")
    @JvmStatic
    fun bindTabMediator(tab: TabLayout?, pager: ViewPager2?, clickable: Boolean?) {
        if (pager!!.adapter == null) return
        TabLayoutMediator(tab!!, pager) { t: TabLayout.Tab, _: Int ->
            t.view.isClickable = clickable!!
            t.view.isFocusable = clickable
        }.attach()
    }

    @JvmStatic
    @BindingAdapter("bindErrorMsg")
    fun bindErrorMsg(t: TextView, s: String?) {
        if (s == null) return
        t.text = s
        if (s.isEmpty()) return
        t.startAnimation(AnimationUtils.loadAnimation(t.context, R.anim.shake))
    }

    @JvmStatic
    @BindingAdapter("bindButtonEnabled")
    fun bindButtonEnabled(v: Button, enabled: Boolean) {
        v.isClickable = enabled
        v.isFocusable = enabled
        v.backgroundTintList = ContextCompat.getColorStateList(
            v.context,
            if (enabled) R.color.colorPrimary else R.color.materialGray6
        )
    }

    @JvmStatic
    @BindingAdapter("bindSecondaryButtonEnabled")
    fun bindSecondaryButtonEnabled(v: Button, enabled: Boolean) {
        v.isClickable = enabled
        v.isFocusable = enabled
        v.setTextColor(
            ContextCompat.getColorStateList(
                v.context,
                if (enabled) R.color.colorPrimary else R.color.white
            )
        )
        v.backgroundTintList = ContextCompat.getColorStateList(
            v.context,
            if (enabled) R.color.colorSecondary else R.color.materialGray6
        )
    }

    @JvmStatic
    @BindingAdapter("bindEditTextAutoFocus")
    fun bindEditTextAutoFocus(e: EditText, enabled: Boolean) {
        if (!enabled) return
        e.requestFocus()
        val a = e.context as Activity
        val imm = a.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm?.showSoftInput(e, InputMethodManager.SHOW_IMPLICIT)
    }

    @JvmStatic
    @BindingAdapter("bindImageUri")
    fun bindImageUri(v: ImageView, uri: Uri?) {
        if (uri != null) v.setImageURI(uri)
    }

    @JvmStatic
    @BindingAdapter("bindImageLink")
    fun bindImageLink(view: ImageView?, link: String?) {
        if (link == null || link.isEmpty()) return
        Glide.with(view!!).load(link).into(view)
    }
}