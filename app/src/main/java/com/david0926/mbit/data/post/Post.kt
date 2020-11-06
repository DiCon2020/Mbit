package com.david0926.mbit.data.post

import com.david0926.mbit.data.UserModel
import com.david0926.mbit.data.comment.Comment
import java.io.Serializable

data class Post(
    var commentList: ArrayList<Any>,
    var empathyList: ArrayList<Any>, // user_id 들어있음
    var _id: String,
    var text: String,
    var photo: String,
    var createdDate: String,
    var owner: UserModel
) : Serializable