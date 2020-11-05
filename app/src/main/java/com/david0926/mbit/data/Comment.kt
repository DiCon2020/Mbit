package com.david0926.mbit.data

data class Comment(
    var _id: String,
    var username: String,
    var photo: String?,
    var text: String,
    var createdDate: String,
    var personalityType: String,
    var reply: Boolean,
    var deleted: Boolean
)