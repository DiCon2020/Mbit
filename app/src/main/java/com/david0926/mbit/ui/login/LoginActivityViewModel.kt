package com.david0926.mbit.ui.login

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.david0926.mbit.data.LoginRequest
import com.david0926.mbit.data.UserModel

class LoginActivityViewModel : ViewModel() {
    val email = MutableLiveData("")
    val pw = MutableLiveData("")

    fun requestLogin(request: LoginRequest){

    }
}