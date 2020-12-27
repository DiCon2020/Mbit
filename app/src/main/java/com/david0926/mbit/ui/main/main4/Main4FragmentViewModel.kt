package com.david0926.mbit.ui.main.main4

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.david0926.mbit.data.UserModel

class Main4FragmentViewModel : ViewModel() {
    val user = MutableLiveData<UserModel>()
    val version = MutableLiveData("")

    val topicChecked = MutableLiveData(false)
    val commentChecked = MutableLiveData(false)
}