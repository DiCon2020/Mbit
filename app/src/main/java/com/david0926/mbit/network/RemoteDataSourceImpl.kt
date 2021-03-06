package com.david0926.mbit.network

import android.util.Log
import com.david0926.mbit.data.UserModel
import com.david0926.mbit.data.auth.LoginRequest
import com.david0926.mbit.data.auth.RegisterRequest
import com.david0926.mbit.data.auth.UpdateInfoRequest
import com.david0926.mbit.data.comment.Comment
import com.david0926.mbit.data.comment.CommentAddRequest
import com.david0926.mbit.data.comment.CommentGetRequest
import com.david0926.mbit.data.comment.CommonResponse
import com.david0926.mbit.data.mail.Mail
import com.david0926.mbit.data.mail.MailGetRequest
import com.david0926.mbit.data.mail.MailItem
import com.david0926.mbit.data.mail.MailSendRequest
import com.david0926.mbit.data.personality.PersonalityResponse
import com.david0926.mbit.data.post.*
import com.david0926.mbit.data.topic.Topic
import com.david0926.mbit.data.topic.TopicCreateRequest
import com.david0926.mbit.data.topic.TopicVoteRequest
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSourceImpl : RemoteDataSource {

    private fun toPart(obj: Any?): RequestBody? {
        if (obj == null) return null
        return RequestBody.create(MediaType.parse("multipart/form-data"), obj.toString())
    }

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

            override fun onResponse(
                call: Call<CommonResponse>,
                response: Response<CommonResponse>
            ) {
                if (response.body() == null) {
                    onResponse(
                        Gson().fromJson(
                            response.errorBody()!!.string(),
                            CommonResponse::class.java
                        ), null
                    )
                } else {
                    val user: UserModel = Gson().fromJson(
                        Gson().toJson(response.body()!!.data),
                        UserModel::class.java
                    )
                    onResponse(response.body()!!, user)
                }
            }
        })
    }

    override fun register(
        registerRequest: RegisterRequest,
        onResponse: (CommonResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {

        MbitRetrofit.authService.register(
            toPart(registerRequest.id)!!,
            toPart(registerRequest.password)!!,
            toPart(registerRequest.username)!!,
            toPart(registerRequest.yearOfBirth)!!,
            toPart(registerRequest.personalityType),
            registerRequest.photo
        )
            .enqueue(object : Callback<CommonResponse> {
                override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                    onFailure(t)
                }

                override fun onResponse(
                    call: Call<CommonResponse>,
                    response: Response<CommonResponse>
                ) {
                    if (response.body() == null) {
                        onResponse(
                            Gson().fromJson(
                                response.errorBody()!!.string(),
                                CommonResponse::class.java
                            )
                        )
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
        MbitRetrofit.authService.getUserData("Bearer $token")
            .enqueue(object : Callback<CommonResponse> {
                override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                    onFailure(t)
                }

                override fun onResponse(
                    call: Call<CommonResponse>,
                    response: Response<CommonResponse>
                ) {
                    if (response.body() == null) {
                        onResponse(
                            Gson().fromJson(
                                response.errorBody()!!.string(),
                                CommonResponse::class.java
                            ), null
                        )
                    } else {
                        val user: UserModel = Gson().fromJson(
                            Gson().toJson(response.body()!!.data),
                            UserModel::class.java
                        )
                        onResponse(response.body()!!, user)
                    }
                }
            })
    }

    override fun setUserData(
        token: String,
        updateInfoRequest: UpdateInfoRequest,
        onResponse: (CommonResponse, UserModel?) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        MbitRetrofit.authService.setUserData(
            "Bearer $token",
            toPart(updateInfoRequest.username),
            toPart(updateInfoRequest.personalityType),
            toPart(updateInfoRequest.topicNotificationStatus),
            toPart(updateInfoRequest.commentNotificationStatus),
            updateInfoRequest.photo,
            toPart(updateInfoRequest.time)
        )
            .enqueue(object : Callback<CommonResponse> {
                override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                    onFailure(t)
                }

                override fun onResponse(
                    call: Call<CommonResponse>,
                    response: Response<CommonResponse>
                ) {
                    if (response.body() == null) {
                        onResponse(
                            Gson().fromJson(
                                response.errorBody()!!.string(),
                                CommonResponse::class.java
                            ), null
                        )
                    } else {
                        val user: UserModel = Gson().fromJson(
                            Gson().toJson(response.body()!!.data),
                            UserModel::class.java
                        )
                        onResponse(response.body()!!, user)
                    }
                }
            })
    }

    override fun deleteUser(
        token: String,
        onResponse: (CommonResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        MbitRetrofit.authService.deleteUser(
            "Bearer $token",
        ).enqueue(object : Callback<CommonResponse> {
            override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                onFailure(t)
            }

            override fun onResponse(
                call: Call<CommonResponse>,
                response: Response<CommonResponse>
            ) {
                try {
                    if (response.body() == null) {
                        onResponse(
                            Gson().fromJson(
                                response.errorBody()!!.string(),
                                CommonResponse::class.java
                            )
                        )
                    } else {
                        onResponse(response.body()!!)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
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
        MbitRetrofit.postService.getPosts(
            "Bearer $token",
            postGetRequest.page,
            postGetRequest.length,
            postGetRequest.personalityType
        ).enqueue(object : Callback<CommonResponse> {
            override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                onFailure(t)
            }

            override fun onResponse(
                call: Call<CommonResponse>,
                response: Response<CommonResponse>
            ) {
                try {
                    if (response.body() == null) {
                        onResponse(
                            Gson().fromJson(
                                response.errorBody()!!.string(),
                                CommonResponse::class.java
                            ), null
                        )
                    } else {
                        Log.d("baam", "onResponse: " + response.body())
                        val type = object : TypeToken<ArrayList<Post>>() {}.type
                        val posts: ArrayList<Post> = Gson().fromJson(
                            Gson().toJson(response.body()!!.data), type
                        )
                        onResponse(response.body()!!, posts)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
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
        MbitRetrofit.postService.createPost(
            "Bearer $token",
            toPart(postCreateRequest.text)!!,
            toPart(postCreateRequest.personalityTypeStatus)!!,
            postCreateRequest.photo
        )
            .enqueue(object : Callback<CommonResponse> {
                override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                    onFailure(t)
                }

                override fun onResponse(
                    call: Call<CommonResponse>,
                    response: Response<CommonResponse>
                ) {
                    if (response.body() == null) {
                        onResponse(
                            Gson().fromJson(
                                response.errorBody()!!.string(),
                                CommonResponse::class.java
                            )
                        )
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
        MbitRetrofit.postService.votePost("Bearer $token", postVoteRequest)
            .enqueue(object : Callback<CommonResponse> {
                override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                    onFailure(t)
                }

                override fun onResponse(
                    call: Call<CommonResponse>,
                    response: Response<CommonResponse>
                ) {
                    if (response.body() == null) {
                        onResponse(
                            Gson().fromJson(
                                response.errorBody()!!.string(),
                                CommonResponse::class.java
                            )
                        )
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
        MbitRetrofit.postService.deletePost("Bearer $token", postDeleteRequest)
            .enqueue(object : Callback<CommonResponse> {
                override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                    onFailure(t)
                }

                override fun onResponse(
                    call: Call<CommonResponse>,
                    response: Response<CommonResponse>
                ) {
                    if (response.body() == null) {
                        onResponse(
                            Gson().fromJson(
                                response.errorBody()!!.string(),
                                CommonResponse::class.java
                            )
                        )
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
        MbitRetrofit.commentService.getComments("Bearer $token", commentGetRequest.post_id)
            .enqueue(object : Callback<CommonResponse> {
                override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                    onFailure(t)
                }

                override fun onResponse(
                    call: Call<CommonResponse>,
                    response: Response<CommonResponse>
                ) {
                    if (response.body() == null) {
                        onResponse(
                            Gson().fromJson(
                                response.errorBody()!!.string(),
                                CommonResponse::class.java
                            ), null
                        )
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
        onResponse: (CommonResponse, ArrayList<Comment>?) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        MbitRetrofit.commentService.addComment("Bearer $token", commentAddRequest)
            .enqueue(object : Callback<CommonResponse> {
                override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                    onFailure(t)
                }

                override fun onResponse(
                    call: Call<CommonResponse>,
                    response: Response<CommonResponse>
                ) {
                    if (response.body() == null) {
                        onResponse(
                            Gson().fromJson(
                                response.errorBody()!!.string(),
                                CommonResponse::class.java
                            ), null
                        )
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

    // PersonalityService

    override fun getPersonalityType(
        token: String,
        onResponse: (CommonResponse, PersonalityResponse?) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        MbitRetrofit.personalityService.getPersonalityType("Bearer $token")
            .enqueue(object : Callback<CommonResponse> {
                override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                    onFailure(t)
                }

                override fun onResponse(
                    call: Call<CommonResponse>,
                    response: Response<CommonResponse>
                ) {
                    if (response.body() == null) {
                        onResponse(
                            Gson().fromJson(
                                response.errorBody()!!.string(),
                                CommonResponse::class.java
                            ), null
                        )
                    } else {
                        val data: PersonalityResponse = Gson().fromJson(
                            Gson().toJson(response.body()!!.data), PersonalityResponse::class.java
                        )
                        onResponse(response.body()!!, data)
                    }
                }
            })
    }

    // TopicService

    override fun getLastTopics(
        token: String,
        onResponse: (CommonResponse, ArrayList<Topic>?) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        MbitRetrofit.topicService.getLastTopics("Bearer $token")
            .enqueue(object : Callback<CommonResponse> {
                override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                    onFailure(t)
                }

                override fun onResponse(
                    call: Call<CommonResponse>,
                    response: Response<CommonResponse>
                ) {
                    if (response.body() == null) {
                        onResponse(
                            Gson().fromJson(
                                response.errorBody()!!.string(),
                                CommonResponse::class.java
                            ), null
                        )
                    } else {
                        val Type = object : TypeToken<ArrayList<Topic>>() {}.type
                        val topics: ArrayList<Topic> = Gson().fromJson<ArrayList<Topic>>(
                            Gson().toJson(response.body()!!.data), Type
                        )
                        onResponse(response.body()!!, topics)
                    }
                }
            })
    }

    override fun getAllTopics(
        token: String,
        onResponse: (CommonResponse, ArrayList<Topic>?) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        MbitRetrofit.topicService.getAllTopics("Bearer $token")
            .enqueue(object : Callback<CommonResponse> {
                override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                    onFailure(t)
                }

                override fun onResponse(
                    call: Call<CommonResponse>,
                    response: Response<CommonResponse>
                ) {
                    if (response.body() == null) {
                        onResponse(
                            Gson().fromJson(
                                response.errorBody()!!.string(),
                                CommonResponse::class.java
                            ), null
                        )
                    } else {
                        val Type = object : TypeToken<ArrayList<Topic>>() {}.type
                        val topics: ArrayList<Topic> = Gson().fromJson<ArrayList<Topic>>(
                            Gson().toJson(response.body()!!.data), Type
                        )
                        onResponse(response.body()!!, topics)
                    }
                }
            })
    }

    override fun createTopic(
        token: String,
        topicCreateRequest: TopicCreateRequest,
        onResponse: (CommonResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        MbitRetrofit.topicService.createTopic("Bearer $token", topicCreateRequest)
            .enqueue(object : Callback<CommonResponse> {
                override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                    onFailure(t)
                }

                override fun onResponse(
                    call: Call<CommonResponse>,
                    response: Response<CommonResponse>
                ) {
                    if (response.body() == null) {
                        onResponse(
                            Gson().fromJson(
                                response.errorBody()!!.string(),
                                CommonResponse::class.java
                            )
                        )
                    } else {
                        onResponse(response.body()!!)
                    }
                }
            })
    }

    override fun voteTopic(
        token: String,
        topicVoteRequest: TopicVoteRequest,
        onResponse: (CommonResponse, ArrayList<Topic>?) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        MbitRetrofit.topicService.voteTopic("Bearer $token", topicVoteRequest)
            .enqueue(object : Callback<CommonResponse> {
                override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                    onFailure(t)
                }

                override fun onResponse(
                    call: Call<CommonResponse>,
                    response: Response<CommonResponse>
                ) {
                    if (response.body() == null) {
                        onResponse(
                            Gson().fromJson(
                                response.errorBody()!!.string(),
                                CommonResponse::class.java
                            ), null
                        )
                    } else {
                        val Type = object : TypeToken<ArrayList<Topic>>() {}.type
                        val topics: ArrayList<Topic>? = Gson().fromJson(
                            Gson().toJson(response.body()!!.data), Type
                        )
                        onResponse(response.body()!!, topics)
                    }
                }
            })
    }

    override fun getMails(
        token: String,
        mailGetRequest: MailGetRequest,
        onResponse: (CommonResponse, ArrayList<Mail>?) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {

        MbitRetrofit.mailService.getMail("Bearer $token", mailGetRequest.your_id)
            .enqueue(object : Callback<CommonResponse> {
                override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                    onFailure(t)
                }

                override fun onResponse(
                    call: Call<CommonResponse>,
                    response: Response<CommonResponse>
                ) {
                    if (response.body() == null) {
                        onResponse(
                            Gson().fromJson(
                                response.errorBody()!!.string(),
                                CommonResponse::class.java
                            ), null
                        )
                    } else {
                        val Type = object : TypeToken<ArrayList<Mail>>() {}.type
                        val mails: ArrayList<Mail> = Gson().fromJson<ArrayList<Mail>>(
                            Gson().toJson(response.body()!!.data), Type
                        )
                        onResponse(response.body()!!, mails)
                    }
                }
            })
    }

    override fun getMailList(
        token: String,
        onResponse: (CommonResponse, ArrayList<MailItem>?) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        MbitRetrofit.mailService.getMyMail("Bearer $token")
            .enqueue(object : Callback<CommonResponse> {
                override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                    onFailure(t)
                }

                override fun onResponse(
                    call: Call<CommonResponse>,
                    response: Response<CommonResponse>
                ) {
                    if (response.body() == null) {
                        onResponse(
                            Gson().fromJson(
                                response.errorBody()!!.string(),
                                CommonResponse::class.java
                            ), null
                        )
                    } else {
                        val Type = object : TypeToken<ArrayList<Mail>>() {}.type
                        val mails: ArrayList<MailItem> = Gson().fromJson<ArrayList<MailItem>>(
                            Gson().toJson(response.body()!!.data), Type
                        )
                        onResponse(response.body()!!, mails)
                    }
                }
            })
    }

    override fun sendMail(
        token: String,
        mailSendRequest: MailSendRequest,
        onResponse: (CommonResponse, ArrayList<Mail>?) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        MbitRetrofit.mailService.sendMail("Bearer $token", mailSendRequest)
            .enqueue(object : Callback<CommonResponse> {
                override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                    onFailure(t)
                }

                override fun onResponse(
                    call: Call<CommonResponse>,
                    response: Response<CommonResponse>
                ) {
                    if (response.body() == null) {
                        onResponse(
                            Gson().fromJson(
                                response.errorBody()!!.string(),
                                CommonResponse::class.java
                            ), null
                        )
                    } else {
                        val Type = object : TypeToken<ArrayList<Mail>>() {}.type
                        val mails: ArrayList<Mail> = Gson().fromJson(
                            Gson().toJson(response.body()!!.data), Type
                        )
                        onResponse(response.body()!!, mails)
                    }
                }
            })
    }
}