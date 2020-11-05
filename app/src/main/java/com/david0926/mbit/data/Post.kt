package com.david0926.mbit.data

data class Post (
    var commentList: ArrayList<Any>,
    var empathyList: ArrayList<Any>,
    var _id: String,
    var text: String,
    var photo: String,
    var createdDate: String,
    var owner: UserModel
)