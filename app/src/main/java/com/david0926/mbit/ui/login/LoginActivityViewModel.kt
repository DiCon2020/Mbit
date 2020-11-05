package com.david0926.mbit.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginActivityViewModel : ViewModel() {
    val email = MutableLiveData("")
    val pw = MutableLiveData("")
    val errorMsg = MutableLiveData("")
}