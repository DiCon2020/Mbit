package com.david0926.mbit.network.topic

import com.david0926.mbit.data.comment.CommonResponse
import com.david0926.mbit.data.topic.TopicCreateRequest
import com.david0926.mbit.data.topic.TopicVoteRequest
import retrofit2.Call
import retrofit2.http.*

interface TopicService {
    @GET("/mbit/topic/get")
    fun getLastTopics(
        @Header("Authorization") token: String
    ): Call<CommonResponse>

    @GET("/mbit/topic/get_all")
    fun getAllTopics(
        @Header("Authorization") token: String
    ): Call<CommonResponse>

    @POST("/mbit/topic/create")
    fun createTopic(
        @Header("Authorization") token: String,
        @Body topicCreateRequest: TopicCreateRequest
    ): Call<CommonResponse>

    @PUT("/mbit/topic/click")
    fun voteTopic(
        @Header("Authorization") token: String,
        @Body topicVoteRequest: TopicVoteRequest
    ): Call<CommonResponse>

}