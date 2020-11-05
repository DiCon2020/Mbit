package com.david0926.mbit.data

data class CommonResponse (
    val status: Int,
    val message: String,
    val accessToken: String,
    val success: Boolean,
    val data: Any
)