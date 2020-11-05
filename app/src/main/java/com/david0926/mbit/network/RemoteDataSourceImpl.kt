package com.david0926.mbit.network

import com.david0926.mbit.data.*
import com.david0926.mbit.data.network.LoginRequest
import com.david0926.mbit.data.network.RegisterRequest
import com.david0926.mbit.data.post.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSourceImpl : RemoteDataSource {


    // AuthService
    override fun login(
        loginRequest: LoginRequest,
        onResponse: (CommonResponse, UserModel?) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        MbitRetrofit.authService.login(loginRequest).enqueue(object : Callback<CommonResponse> {
            override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                onFailure(t)
            }
            override fun onResponse(call: Call<CommonResponse>, response: Response<CommonResponse>) {
                if(response.body() == null) {
                    onResponse(Gson().fromJson(response.errorBody()!!.string(), CommonResponse::class.java), null)
                } else {
                    var user: UserModel = Gson().fromJson(Gson().toJson(response.body()!!.data), UserModel::class.java)
                    onResponse(response.body()!!, user);
                }
            }
        })
    }

    override fun register(
        registerRequest: RegisterRequest,
        onResponse: (CommonResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        MbitRetrofit.authService.register(registerRequest).enqueue(object : Callback<CommonResponse> {
            override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                onFailure(t)
            }
            override fun onResponse(call: Call<CommonResponse>, response: Response<CommonResponse>) {
                if(response.body() == null) {
                    onResponse(Gson().fromJson(response.errorBody()!!.string(), CommonResponse::class.java))
                } else {
                    onResponse(response.body()!!)
                }
            }
        })
    }

    override fun getUserData(
        token: String,
        onResponse: (CommonResponse, UserModel?) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        MbitRetrofit.authService.getUserData("Bearer $token").enqueue(object : Callback<CommonResponse> {
            override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                onFailure(t)
            }
            override fun onResponse(call: Call<CommonResponse>, response: Response<CommonResponse>) {
                if(response.body() == null) {
                    onResponse(Gson().fromJson(response.errorBody()!!.string(), CommonResponse::class.java), null)
                } else {
                    var user: UserModel = Gson().fromJson(Gson().toJson(response.body()!!.data), UserModel::class.java)
                    onResponse(response.body()!!, user)
                }
            }
        })
    }

    override fun setUserData(
        token: String,
        userModel: UserModel,
        onResponse: (CommonResponse, UserModel?) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        MbitRetrofit.authService.setUserData("Bearer $token", userModel).enqueue(object : Callback<CommonResponse> {
            override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                onFailure(t)
            }
            override fun onResponse(call: Call<CommonResponse>, response: Response<CommonResponse>) {
                if(response.body() == null) {
                    onResponse(Gson().fromJson(response.errorBody()!!.string(), CommonResponse::class.java), null)
                } else {
                    var user: UserModel = Gson().fromJson(Gson().toJson(response.body()!!.data), UserModel::class.java)
                    onResponse(response.body()!!, user)
                }
            }
        })
    }

    // PostService

    override fun getPosts(
        token: String,
        postGetRequest: PostGetRequest,
        onResponse: (CommonResponse, ArrayList<Post>?) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        MbitRetrofit.postService.getPosts("Bearer $token", postGetRequest.page, postGetRequest.length, postGetRequest.personalityType).enqueue(object : Callback<CommonResponse> {
            override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                onFailure(t)
            }
            override fun onResponse(call: Call<CommonResponse>, response: Response<CommonResponse>) {
                if(response.body() == null) {
                    onResponse(Gson().fromJson(response.errorBody()!!.string(), CommonResponse::class.java), null)
                } else {
                    val Type = object : TypeToken<ArrayList<Post>>() {}.type
                    val posts: ArrayList<Post> = Gson().fromJson<ArrayList<Post>>(
                        Gson().toJson(response.body()!!.data), Type
                    )
                    onResponse(response.body()!!, posts)
                }
            }
        })
    }

    override fun createPost(
        token: String,
        postCreateRequest: PostCreateRequest,
        onResponse: (CommonResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        MbitRetrofit.postService.createPost("Bearer $token", postCreateRequest).enqueue(object : Callback<CommonResponse> {
            override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                onFailure(t)
            }
            override fun onResponse(call: Call<CommonResponse>, response: Response<CommonResponse>) {
                if(response.body() == null) {
                    onResponse(Gson().fromJson(response.errorBody()!!.string(), CommonResponse::class.java))
                } else {
                    onResponse(response.body()!!)
                }
            }
        })
    }

    override fun votePost(
        token: String,
        postVoteRequest: PostVoteRequest,
        onResponse: (CommonResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        MbitRetrofit.postService.votePost("Bearer $token", postVoteRequest).enqueue(object : Callback<CommonResponse> {
            override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                onFailure(t)
            }
            override fun onResponse(call: Call<CommonResponse>, response: Response<CommonResponse>) {
                if(response.body() == null) {
                    onResponse(Gson().fromJson(response.errorBody()!!.string(), CommonResponse::class.java))
                } else {
                    onResponse(response.body()!!)
                }
            }
        })
    }

    override fun deletePost(
        token: String,
        postDeleteRequest: PostDeleteRequest,
        onResponse: (CommonResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        MbitRetrofit.postService.deletePost("Bearer $token", postDeleteRequest).enqueue(object : Callback<CommonResponse> {
            override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                onFailure(t)
            }
            override fun onResponse(call: Call<CommonResponse>, response: Response<CommonResponse>) {
                if(response.body() == null) {
                    onResponse(Gson().fromJson(response.errorBody()!!.string(), CommonResponse::class.java))
                } else {
                    onResponse(response.body()!!)
                }
            }
        })
    }

    // CommentService

    override fun getComments(
        token: String,
        commentGetRequest: CommentGetRequest,
        onResponse: (CommonResponse, ArrayList<Comment>?) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        MbitRetrofit.commentService.getComments("Bearer $token", commentGetRequest.post_id).enqueue(object : Callback<CommonResponse> {
            override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                onFailure(t)
            }
            override fun onResponse(call: Call<CommonResponse>, response: Response<CommonResponse>) {
                if(response.body() == null) {
                    onResponse(Gson().fromJson(response.errorBody()!!.string(), CommonResponse::class.java), null)
                } else {
                    val Type = object : TypeToken<ArrayList<Comment>>() {}.type
                    val comments: ArrayList<Comment> = Gson().fromJson<ArrayList<Comment>>(
                        Gson().toJson(response.body()!!.data), Type
                    )
                    onResponse(response.body()!!, comments)
                }
            }
        })
    }

    override fun addComment(
        token: String,
        commentAddRequest: CommentAddRequest,
        onResponse: (CommonResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        MbitRetrofit.commentService.addComment("Bearer $token", commentAddRequest).enqueue(object : Callback<CommonResponse> {
            override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                onFailure(t)
            }
            override fun onResponse(call: Call<CommonResponse>, response: Response<CommonResponse>) {
                if(response.body() == null) {
                    onResponse(Gson().fromJson(response.errorBody()!!.string(), CommonResponse::class.java))
                } else {
                    onResponse(response.body()!!)
                }
            }
        })
    }


}