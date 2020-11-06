package com.david0926.mbit.network

import com.david0926.mbit.network.auth.AuthService
import com.david0926.mbit.network.comment.CommentService
import com.david0926.mbit.network.personality.PersonalityService
import com.david0926.mbit.network.post.PostService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MbitRetrofit {
    private const val baseUrl = "https://api.taemin.dev/";

    private val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()

    val authService: AuthService = retrofit.create(AuthService::class.java)
    val postService: PostService = retrofit.create(PostService::class.java)
    val commentService: CommentService = retrofit.create(CommentService::class.java)
    val personalityService: PersonalityService = retrofit.create(PersonalityService::class.java)

}