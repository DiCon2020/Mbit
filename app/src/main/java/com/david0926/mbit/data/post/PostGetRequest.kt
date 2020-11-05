package com.david0926.mbit.data.post

data class PostGetRequest (
    var page: Int,
    var length: Int,
    var personalityType: String
)