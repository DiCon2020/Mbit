package com.david0926.mbit.ui.Retrofit.Model

import okhttp3.MultipartBody

data class UserModel (
    var id: String,
    var password: String,
    var username: String,
    var yearOfBirth: Int,
    var personalityType: String,
    var photo: String, // 주소로 받겠지?
    var token: String? //  가입할때 토큰과 photo는 Null로 둬도 됩니다
)
