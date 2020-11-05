package com.david0926.mbit.data.post

import okhttp3.MultipartBody

data class PostCreateRequest (
    var text: String,
    var photo: MultipartBody.Part?
)