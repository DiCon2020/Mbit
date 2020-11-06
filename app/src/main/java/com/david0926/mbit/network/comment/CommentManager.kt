package com.david0926.mbit.network.comment

import com.david0926.mbit.data.comment.Comment
import com.david0926.mbit.data.comment.CommentAddRequest
import com.david0926.mbit.data.comment.CommentGetRequest
import com.david0926.mbit.data.comment.CommonResponse
import com.david0926.mbit.network.RemoteDataSource
import com.david0926.mbit.network.RemoteDataSourceImpl

class CommentManager {
    private val retrofitRemoteDataSource: RemoteDataSource = RemoteDataSourceImpl()

    /**
     * 게시글의 댓글 가져올때 사용
     * CommentGetRequest에 post_id넣어주면 그 게시글의 댓글들 들어옴
     */
    fun getComments(token: String, commentGetRequest: CommentGetRequest, onResponse : (CommonResponse, ArrayList<Comment>?) -> Unit, onFailure: (Throwable) -> Unit) {
        retrofitRemoteDataSource.getComments(token, commentGetRequest, onResponse, onFailure)
    }

    /**
     *  게시글에 댓글 추가함
     *  CommentAddRequest에 post_id(댓글 달 게시글), comment_id, text(댓글 내용) 들어감.
     *  comment_id에 null이나 공백 넣어주면 일반 댓글로 인식,
     *  만약 값이 들어있으면 답글로 인식
     */
    fun addComment(token: String, commentAddRequest: CommentAddRequest, onResponse : (CommonResponse, ArrayList<Comment>?) -> Unit, onFailure: (Throwable) -> Unit) {
        retrofitRemoteDataSource.addComment(token, commentAddRequest, onResponse, onFailure)
    }
}