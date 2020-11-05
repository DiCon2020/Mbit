package com.david0926.mbit.data

data class CommentAddRequest (
    var post_id: String,
    var comment_id: String?,
    var text: String
)