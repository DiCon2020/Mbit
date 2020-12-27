package com.david0926.mbit.data.comment

data class CommentAddRequest(
    var post_id: String,
    var comment_id: String?,
    var text: String,
    var topic: Boolean?
)