package com.david0926.mbit.network

import com.david0926.mbit.data.*
import com.david0926.mbit.data.comment.Comment
import com.david0926.mbit.data.comment.CommentAddRequest
import com.david0926.mbit.data.comment.CommentGetRequest
import com.david0926.mbit.data.comment.CommonResponse
import com.david0926.mbit.data.auth.LoginRequest
import com.david0926.mbit.data.auth.RegisterRequest
import com.david0926.mbit.data.auth.UpdateInfoRequest
import com.david0926.mbit.data.personality.PersonalityResponse
import com.david0926.mbit.data.post.*

interface RemoteDataSource {

    // AuthService
    fun login(
        loginRequest: LoginRequest,
        onResponse : (CommonResponse, UserModel?) -> Unit,
        onFailure: (Throwable) -> Unit
    )

    fun register(
        registerRequest: RegisterRequest,
        onResponse : (CommonResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    )

    fun getUserData(
        token: String,
        onResponse : (CommonResponse, UserModel?) -> Unit,
        onFailure: (Throwable) -> Unit
    )

    fun setUserData(
        token: String,
        updateInfoRequest: UpdateInfoRequest,
        onResponse : (CommonResponse, UserModel?) -> Unit,
        onFailure: (Throwable) -> Unit
    )

    // PostService
    fun getPosts(
        token: String,
        postGetRequest: PostGetRequest,
        onResponse : (CommonResponse, ArrayList<Post>?) -> Unit,
        onFailure: (Throwable) -> Unit
    )

    fun createPost(
        token: String,
        postCreateRequest: PostCreateRequest,
        onResponse : (CommonResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    )

    fun votePost(
        token: String,
        postVoteRequest: PostVoteRequest,
        onResponse : (CommonResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    )

    fun deletePost(
        token: String,
        postDeleteRequest: PostDeleteRequest,
        onResponse : (CommonResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    )

    // CommentService
    fun getComments(
        token: String,
        commentGetRequest: CommentGetRequest,
        onResponse : (CommonResponse, ArrayList<Comment>?) -> Unit,
        onFailure: (Throwable) -> Unit
    )

    fun addComment(
        token: String,
        commentAddRequest: CommentAddRequest,
        onResponse : (CommonResponse, ArrayList<Comment>?) -> Unit,
        onFailure: (Throwable) -> Unit
    )

    // PersonalityService
    fun getPersonalityType(
        token: String,
        onResponse : (CommonResponse, PersonalityResponse?) -> Unit,
        onFailure: (Throwable) -> Unit
    )
}