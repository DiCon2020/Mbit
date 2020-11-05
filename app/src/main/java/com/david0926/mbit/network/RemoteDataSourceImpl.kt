package com.david0926.mbit.network

import com.david0926.mbit.data.CommonResponse
import com.david0926.mbit.data.LoginRequest
import com.david0926.mbit.data.RegisterRequest
import com.david0926.mbit.data.UserModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSourceImpl : RemoteDataSource {

    override fun login(
        loginRequest: LoginRequest,
        onResponse: (CommonResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        MbitRetrofit.authService.login(loginRequest).enqueue(object : Callback<CommonResponse> {
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
        onResponse: (CommonResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        MbitRetrofit.authService.getUserData("Bearer $token").enqueue(object : Callback<CommonResponse> {
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

    override fun setUserData(
        token: String,
        userModel: UserModel,
        onResponse: (CommonResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        MbitRetrofit.authService.setUserData("Bearer $token", userModel).enqueue(object : Callback<CommonResponse> {
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