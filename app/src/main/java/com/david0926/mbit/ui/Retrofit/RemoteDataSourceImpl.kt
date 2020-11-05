package com.david0926.mbit.ui.Retrofit

import android.util.Log
import com.david0926.mbit.ui.Retrofit.Model.CommonResponse
import com.david0926.mbit.ui.Retrofit.Model.LoginModel
import com.david0926.mbit.ui.Retrofit.Model.RegisterModel
import com.david0926.mbit.ui.Retrofit.Model.UserModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSourceImpl : RemoteDataSource {

    override fun login(
        loginModel: LoginModel,
        onResponse: (CommonResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        MbitRetrofit.authService.login(loginModel).enqueue(object : Callback<CommonResponse> {
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
        registerModel: RegisterModel,
        onResponse: (CommonResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        MbitRetrofit.authService.register(registerModel).enqueue(object : Callback<CommonResponse> {
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