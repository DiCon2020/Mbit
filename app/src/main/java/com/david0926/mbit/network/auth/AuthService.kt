package com.david0926.mbit.network.auth

import com.david0926.mbit.data.auth.LoginRequest
import com.david0926.mbit.data.comment.CommonResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface AuthService {

    @POST("/mbit/auth/signin")
    fun login(
        @Body loginmodel: LoginRequest
    ): Call<CommonResponse>

    @Multipart
    @POST("/mbit/auth/signup")
    fun register(
        @Part("id") id: RequestBody,
        @Part("password") password: RequestBody,
        @Part("username") username: RequestBody,
        @Part("yearOfBirth") yearOfBirth: RequestBody,
        @Part("personalityType") personalityType: RequestBody?,
        @Part photo: MultipartBody.Part?
    ): Call<CommonResponse>

    @POST("/mbit/auth/info")
    fun getUserData(
        @Header("Authorization") token: String
    ): Call<CommonResponse>

    @Multipart
    @PUT("/mbit/auth/update_info")
    fun setUserData(
        @Header("Authorization") token: String,
        @Part("username") username: RequestBody?,
        @Part("personalityType") personalityType: RequestBody?,
        @Part("topicNotificationStatus") topicNotificationStatus: RequestBody?,
        @Part("commentNotificationStatus") commentNotificationStatus: RequestBody?,
        @Part photo: MultipartBody.Part?,
        @Part("time") time: RequestBody?,
    ): Call<CommonResponse>

    @DELETE("/mbit/auth/delete")
    fun deleteUser(
        @Header("Authorization") token: String,
    ): Call<CommonResponse>

}