package com.david0926.mbit.ui.Retrofit.Model

data class CommonResponse (
    val status: Int,
    val message: String,
    val accessToken: String,
    val success: Boolean,
    val data: Any
)