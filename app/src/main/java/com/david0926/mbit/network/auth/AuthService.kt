package com.david0926.mbit.network.auth

import com.david0926.mbit.data.comment.CommonResponse
import com.david0926.mbit.data.network.LoginRequest
import com.david0926.mbit.data.network.RegisterRequest
import com.david0926.mbit.data.UserModel
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

    @Multipart
    @PUT("/mbit/auth/info")
    fun setUserData(
        @Header("Authorization") token: String,
        @Part userModel: UserModel
    ): Call<CommonResponse>
}