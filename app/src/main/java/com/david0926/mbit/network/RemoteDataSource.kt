package com.david0926.mbit.network

import com.david0926.mbit.data.*
import com.david0926.mbit.data.network.LoginRequest
import com.david0926.mbit.data.network.RegisterRequest
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
        userModel: UserModel,
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
}