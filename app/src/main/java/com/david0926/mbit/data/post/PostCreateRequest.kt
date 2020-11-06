package com.david0926.mbit.data.post

import okhttp3.MultipartBody

data class PostCreateRequest(
    var text: String,
    var personalityTypeStatus: Boolean,
    var photo: MultipartBody.Part?
)