package com.david0926.mbit.ui.Retrofit

import com.david0926.mbit.ui.Retrofit.Model.CommonResponse
import com.david0926.mbit.ui.Retrofit.Model.LoginModel
import com.david0926.mbit.ui.Retrofit.Model.RegisterModel
import com.david0926.mbit.ui.Retrofit.Model.UserModel
import retrofit2.Response

interface RemoteDataSource {
    fun login(
        loginModel: LoginModel,
        onResponse : (CommonResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    )

    fun register(
        registerModel: RegisterModel,
        onResponse : (CommonResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    )

    fun getUserData(
        token: String,
        onResponse : (CommonResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    )

    fun setUserData(
        token: String,
        userModel: UserModel,
        onResponse : (CommonResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    )

}