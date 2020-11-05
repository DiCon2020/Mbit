package com.david0926.mbit.network

import com.david0926.mbit.network.auth.AuthService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MbitRetrofit {
    private const val baseUrl = "https://api.taemin.dev/";

    private val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()

    val authService: AuthService = retrofit.create(AuthService::class.java)

}