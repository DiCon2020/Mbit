package com.david0926.mbit.network.comment

import com.david0926.mbit.data.CommentAddRequest
import com.david0926.mbit.data.CommonResponse
import retrofit2.Call
import retrofit2.http.*

interface CommentService {

    @GET("/mbit/comment/get_all")
    fun getComments(
        @Header("Authorization") token: String,
        @Query("post_id") post_id: String
    ): Call<CommonResponse>

    @POST("/mbit/comment/create")
    fun addComment(
        @Header("Authorization") token: String,
        @Body addCommentRequest: CommentAddRequest
    ): Call<CommonResponse>
}