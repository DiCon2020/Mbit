package com.david0926.mbit.network.auth

import com.david0926.mbit.data.*
import com.david0926.mbit.data.post.*
import com.david0926.mbit.network.RemoteDataSource
import com.david0926.mbit.network.RemoteDataSourceImpl

class PostManager {
    private val retrofitRemoteDataSource: RemoteDataSource = RemoteDataSourceImpl()

    /**
     *  게시글 가져올 때 사용함
     *  PostGetRequest에는 page(페이지), length(페이지 당 게시글 수), personalityType(MBTI)가 들어감
     */
    fun getPosts(token: String, postGetRequest: PostGetRequest, onResponse : (CommonResponse, ArrayList<Post>?) -> Unit, onFailure: (Throwable) -> Unit) {
        retrofitRemoteDataSource.getPosts(token, postGetRequest, onResponse, onFailure)
    }

    /**
     *  게시글 만들 때 사용함
     *  PostCreateRequest에는 text(글 내용), photo(사진)이 들어감
     */
    fun createPost(token: String, postCreateRequest: PostCreateRequest, onResponse : (CommonResponse) -> Unit, onFailure: (Throwable) -> Unit) {
        retrofitRemoteDataSource.createPost(token, postCreateRequest, onResponse, onFailure)
    }

    /**
     *  게시글 추천할때 사용함
     *  PostVoteRequest에는 추천할 게시글의 id를 넣어주면 됨.
     */
    fun votePost(token: String, postVoteRequest: PostVoteRequest, onResponse : (CommonResponse) -> Unit, onFailure: (Throwable) -> Unit) {
        retrofitRemoteDataSource.votePost(token, postVoteRequest, onResponse, onFailure)
    }

    /**
     *  게시글 삭제할때 사용함
     *  PostDeleteRequest에는 추천할 게시글의 id를 넣어주면 됨.
     */
    fun deletePost(token: String, postDeleteRequest: PostDeleteRequest, onResponse : (CommonResponse) -> Unit, onFailure: (Throwable) -> Unit) {
        retrofitRemoteDataSource.deletePost(token, postDeleteRequest, onResponse, onFailure)
    }
}