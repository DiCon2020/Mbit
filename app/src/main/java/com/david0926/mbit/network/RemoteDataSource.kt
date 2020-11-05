package com.david0926.mbit.network

import com.david0926.mbit.data.CommonResponse
import com.david0926.mbit.data.LoginRequest
import com.david0926.mbit.data.RegisterRequest
import com.david0926.mbit.data.UserResponse

interface RemoteDataSource {
    fun login(
        loginRequest: LoginRequest,
        onResponse : (CommonResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    )

    fun register(
        registerRequest: RegisterRequest,
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
        userResponse: UserResponse,
        onResponse : (CommonResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    )

}