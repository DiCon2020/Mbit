package com.david0926.mbit.data

import okhttp3.MultipartBody

data class RegisterRequest (
    var id: String,
    var password: String,
    var username: String,
    var yearOfBirth: Int,
    var personalityType: String,
    var photo: MultipartBody.Part?,
    var token: String? //  가입할때 토큰과 photo는 Null로 둬도 됩니다
)
