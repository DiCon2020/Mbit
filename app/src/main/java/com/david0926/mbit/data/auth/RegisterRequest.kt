package com.david0926.mbit.data.auth

import okhttp3.MultipartBody

data class RegisterRequest(
    var id: String,
    var password: String,
    var username: String,
    var yearOfBirth: Int,
    var personalityType: String?,
    var photo: MultipartBody.Part?
)
