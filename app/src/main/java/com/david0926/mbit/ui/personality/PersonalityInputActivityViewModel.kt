package com.david0926.mbit.ui.personality

import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.david0926.mbit.R

class PersonalityInputActivityViewModel : ViewModel() {

    val personality = MutableLiveData("")
    val selected = ObservableArrayList<String>()

    init {
        for (i in 0..3) selected.add("")
    }

    companion object {
        @BindingAdapter("bindButtonSelected")
        @JvmStatic
        fun bindButtonSelected(btn: Button?, selected: Boolean?) {
            if (btn == null || selected == null) return
            val context = btn.context

            btn.background = ContextCompat.getDrawable(
                context,
                if (selected) R.drawable.round_box else R.drawable.round_box_border
            )

            btn.backgroundTintList =
                if (selected) ContextCompat.getColorStateList(context, R.color.colorPrimary)
                else null

            btn.setTextColor(
                ContextCompat.getColor(
                    context,
                    if (selected) R.color.white else R.color.colorPrimary
                )
            )
        }
    }
}