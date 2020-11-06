package com.david0926.mbit.network.personality

import com.david0926.mbit.data.comment.Comment
import com.david0926.mbit.data.comment.CommentGetRequest
import com.david0926.mbit.data.comment.CommonResponse
import com.david0926.mbit.data.personality.PersonalityResponse
import com.david0926.mbit.network.RemoteDataSource
import com.david0926.mbit.network.RemoteDataSourceImpl

class PersonalityManager {
    private val retrofitRemoteDataSource: RemoteDataSource = RemoteDataSourceImpl()

    /**
     * 성격 유형 정보 가져올 때 사용
     * 토큰만 넣어줍쇼
     */
    fun getPersonalityType(token: String, onResponse : (CommonResponse, PersonalityResponse?) -> Unit, onFailure: (Throwable) -> Unit) {
        retrofitRemoteDataSource.getPersonalityType(token, onResponse, onFailure)
    }

}