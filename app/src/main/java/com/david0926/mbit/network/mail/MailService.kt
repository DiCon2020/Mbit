package com.david0926.mbit.network.mail

import com.david0926.mbit.data.comment.CommonResponse
import com.david0926.mbit.data.mail.MailSendRequest
import com.david0926.mbit.data.post.PostVoteRequest
import retrofit2.Call
import retrofit2.http.*

interface MailService {

    @GET("/mbit/mail/get")
    fun getMail(
        @Header("Authorization") token: String,
        @Query("your_id") target_ID : String
    ): Call<CommonResponse>

    @GET("/mbit/mail/get_list")
    fun getMyMail(
        @Header("Authorization") token: String
    ): Call<CommonResponse>


    @POST("/mbit/mail/send")
    fun sendMail(
        @Header("Authorization") token: String,
        @Body mailSendRequest: MailSendRequest
    ): Call<CommonResponse>



}