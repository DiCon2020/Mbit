package com.david0926.mbit.network.auth

import com.david0926.mbit.data.CommonResponse
import com.david0926.mbit.data.PostCreateRequest
import com.david0926.mbit.data.PostGetRequest
import com.david0926.mbit.data.PostVoteRequest
import retrofit2.Call
import retrofit2.http.*

interface PostService {

    @GET("/mbit/post/get_all")
    fun getPosts(
        @Header("Authorization") token: String,
        @Query("page") page: Int,
        @Query("length") length: Int,
        @Query("personalityType") type: String
    ): Call<CommonResponse>

    @POST("/mbit/post/create")
    fun createPost(
        @Header("Authorization") token: String,
        @Body postCreateRequest: PostCreateRequest
    ): Call<CommonResponse>

    @POST("/mbit/post/empathy")
    fun votePost(
        @Header("Authorization") token: String,
        @Body postVoteRequest: PostVoteRequest
    ): Call<CommonResponse>

    @POST("/mbit/post/empathy")
    fun deletePost(
        @Header("Authorization") token: String,
        @Body postVoteRequest: PostVoteRequest
    ): Call<CommonResponse>
}