package com.david0926.mbit.network.auth

import com.david0926.mbit.data.comment.Comment
import com.david0926.mbit.data.comment.CommentAddRequest
import com.david0926.mbit.data.comment.CommentGetRequest
import com.david0926.mbit.data.comment.CommonResponse
import com.david0926.mbit.network.RemoteDataSource
import com.david0926.mbit.network.RemoteDataSourceImpl

class CommentManager {
    private val retrofitRemoteDataSource: RemoteDataSource = RemoteDataSourceImpl()

    /**
     * 정보 가져올 때 사용함
     * 토큰은.. 이건 뭐 언제받는건지.. 하튼 토큰 넣으세요...^^
     * CommonResponse에 data라는 객체가 있는데 여기에 유저정보 들어있습니다.
     */
    fun getComments(token: String, commentGetRequest: CommentGetRequest, onResponse : (CommonResponse, ArrayList<Comment>?) -> Unit, onFailure: (Throwable) -> Unit) {
        retrofitRemoteDataSource.getComments(token, commentGetRequest, onResponse, onFailure)
    }

    /**
     *  정보 수정할 때 사용함
     *  토큰 넣어주고, 바뀐 유저모델 넣어주시면 반영됩니당
     */
    fun addComment(token: String, commentAddRequest: CommentAddRequest, onResponse : (CommonResponse) -> Unit, onFailure: (Throwable) -> Unit) {
        retrofitRemoteDataSource.addComment(token, commentAddRequest, onResponse, onFailure)
    }
}