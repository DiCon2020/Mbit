package com.david0926.mbit.network.personality

import com.david0926.mbit.data.comment.CommonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface PersonalityService {

    @GET("/mbit/personality_type/get")
    fun getPersonalityType(
        @Header("Authorization") token: String
    ): Call<CommonResponse>

}