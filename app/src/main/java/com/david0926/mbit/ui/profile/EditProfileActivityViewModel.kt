package com.david0926.mbit.ui.profile

import android.net.Uri
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.david0926.mbit.R
import com.david0926.mbit.data.UserModel

class EditProfileActivityViewModel : ViewModel() {

    lateinit var user: UserModel
    val errorMsg = MutableLiveData("")

    val profile = MutableLiveData<Uri>()
    val username = MutableLiveData("")
    val personality = MutableLiveData("")

    companion object {
        @JvmStatic
        @BindingAdapter("bindSpinnerPersonality")
        fun bindSpinnerPersonality(spinner: Spinner, p: String?) {
            if (p == null || p.isEmpty()) return
            val personalities = spinner.context.resources.getStringArray(R.array.personality)
            spinner.setSelection(personalities.indexOf(p))
        }
    }

}