package com.david0926.mbit.ui.register

import android.net.Uri
import androidx.databinding.ObservableArrayList
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {

    //activity
    val page = MutableLiveData(0)
    val fragments = ObservableArrayList<Fragment>()
    val errorMsg = MutableLiveData("")

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