package com.david0926.mbit.network.topic

import com.david0926.mbit.data.comment.Comment
import com.david0926.mbit.data.comment.CommentGetRequest
import com.david0926.mbit.data.comment.CommonResponse
import com.david0926.mbit.data.topic.Topic
import com.david0926.mbit.data.topic.TopicCreateRequest
import com.david0926.mbit.data.topic.TopicVoteRequest
import com.david0926.mbit.network.RemoteDataSource
import com.david0926.mbit.network.RemoteDataSourceImpl

class TopicManager {
    private val retrofitRemoteDataSource: RemoteDataSource = RemoteDataSourceImpl()

    /**
     * 최근 토픽(오늘&어제 토빅) 가져옴
     */
    fun getLastTopics(token: String, onResponse : (CommonResponse, ArrayList<Topic>?) -> Unit, onFailure: (Throwable) -> Unit) {
        retrofitRemoteDataSource.getLastTopics(token, onResponse, onFailure)
    }

    /**
     * 모든 토픽 가져옴
     */
    fun getAllTopics(token: String, onResponse : (CommonResponse, ArrayList<Topic>?) -> Unit, onFailure: (Throwable) -> Unit) {
        retrofitRemoteDataSource.getAllTopics(token, onResponse, onFailure)
    }

    /**
     * 토픽 만들기
     */
    fun createTopic(token: String, topicCreateRequest: TopicCreateRequest, onResponse : (CommonResponse) -> Unit, onFailure: (Throwable) -> Unit) {
        retrofitRemoteDataSource.createTopic(token, topicCreateRequest, onResponse, onFailure)
    }

    /**
     * 토픽 누른거 서버에 적용시키기
     */
    fun voteTopic(token: String, topicVoteRequest: TopicVoteRequest, onResponse : (CommonResponse, ArrayList<Topic>?) -> Unit, onFailure: (Throwable) -> Unit) {
        retrofitRemoteDataSource.voteTopic(token, topicVoteRequest, onResponse, onFailure)
    }
}