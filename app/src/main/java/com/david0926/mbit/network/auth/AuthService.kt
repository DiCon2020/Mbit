package com.david0926.mbit.network.auth

import com.david0926.mbit.data.auth.LoginRequest
import com.david0926.mbit.data.auth.RegisterRequest
import com.david0926.mbit.data.auth.UpdateInfoRequest
import com.david0926.mbit.data.comment.CommonResponse
import retrofit2.Call
import retrofit2.http.*

interface AuthService {

    @POST("/mbit/auth/signin")
    fun login(
        @Body loginmodel: LoginRequest
    ): Call<CommonResponse>

    @POST("/mbit/auth/signup")
    fun register(
        @Body registerRequest: RegisterRequest
    ): Call<CommonResponse>

    @POST("/mbit/auth/info")
    fun getUserData(
        @Header("Authorization") token: String
    ): Call<CommonResponse>


    @PUT("/mbit/auth/update_info")
    fun setUserData(
        @Header("Authorization") token: String,
        @Body updateInfoRequest: UpdateInfoRequest
    ): Call<CommonResponse>
}