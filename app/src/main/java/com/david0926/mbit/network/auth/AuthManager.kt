package com.david0926.mbit.network.auth

import com.david0926.mbit.data.comment.CommonResponse
import com.david0926.mbit.data.auth.LoginRequest
import com.david0926.mbit.data.auth.RegisterRequest
import com.david0926.mbit.data.UserModel
import com.david0926.mbit.data.auth.UpdateInfoRequest
import com.david0926.mbit.network.RemoteDataSource
import com.david0926.mbit.network.RemoteDataSourceImpl

class AuthManager {
    private val retrofitRemoteDataSource: RemoteDataSource = RemoteDataSourceImpl()

    /**
     *  로그인 할때 사용함
     *  LoginModel에는 id와 pw가 들어감
     */
    fun login(loginRequest: LoginRequest, onResponse : (CommonResponse, UserModel?) -> Unit, onFailure: (Throwable) -> Unit) {
        retrofitRemoteDataSource.login(loginRequest, onResponse, onFailure)
    }

    /**
     *  회원가입 할때 사용함
     *  UserModel에는 모든 회원정보가 들어감
     */
    fun register(registerRequest: RegisterRequest, onResponse : (CommonResponse) -> Unit, onFailure: (Throwable) -> Unit) {
        retrofitRemoteDataSource.register(registerRequest, onResponse, onFailure)
    }

    /**
     * 정보 가져올 때 사용함
     * 토큰은.. 이건 뭐 언제받는건지.. 하튼 토큰 넣으세요...^^
     * CommonResponse에 data라는 객체가 있는데 여기에 유저정보 들어있습니다.
     */
    fun getUserData(token: String, onResponse : (CommonResponse, UserModel?) -> Unit, onFailure: (Throwable) -> Unit) {
        retrofitRemoteDataSource.getUserData(token, onResponse, onFailure)
    }

    /**
     *  정보 수정할 때 사용함
     *  토큰 넣어주고, 바뀐 유저모델 넣어주시면 반영됩니당
     */
    fun setUserData(token: String, updateInfoRequest: UpdateInfoRequest, onResponse : (CommonResponse, UserModel?) -> Unit, onFailure: (Throwable) -> Unit) {
        retrofitRemoteDataSource.setUserData(token, updateInfoRequest, onResponse, onFailure)
    }
}