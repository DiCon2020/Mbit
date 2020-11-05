package com.david0926.mbit.ui.Retrofit.Auth

import com.david0926.mbit.ui.Retrofit.Model.CommonResponse
import com.david0926.mbit.ui.Retrofit.Model.LoginModel
import com.david0926.mbit.ui.Retrofit.Model.RegisterModel
import com.david0926.mbit.ui.Retrofit.Model.UserModel
import retrofit2.Call
import retrofit2.http.*

interface AuthService {

    @POST("/mbit/auth/signin")
    fun login(
        @Body loginmodel: LoginModel
    ): Call<CommonResponse>

    @POST("/mbit/auth/signup")
    fun register(
        @Body registerModel: RegisterModel
    ): Call<CommonResponse>

    @POST("/mbit/auth/info")
    fun getUserData(
        @Header("Authorization") token: String
    ): Call<CommonResponse>

    @PUT("/mbit/auth/info")
    fun setUserData(
        @Header("Authorization") token: String,
        @Part userModel: UserModel
    ): Call<CommonResponse>
}