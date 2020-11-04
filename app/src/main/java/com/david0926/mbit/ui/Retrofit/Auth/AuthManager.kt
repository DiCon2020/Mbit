package com.david0926.mbit.ui.Retrofit.Auth

import com.david0926.mbit.ui.Retrofit.Model.CommonResponse
import com.david0926.mbit.ui.Retrofit.Model.LoginModel
import com.david0926.mbit.ui.Retrofit.Model.RegisterModel
import com.david0926.mbit.ui.Retrofit.Model.UserModel
import com.david0926.mbit.ui.Retrofit.RemoteDataSource
import com.david0926.mbit.ui.Retrofit.RemoteDataSourceImpl
import retrofit2.Response

class AuthManager {
    private val retrofitRemoteDataSource: RemoteDataSource = RemoteDataSourceImpl()

    /**
     *  로그인 할때 사용함
     *  LoginModel에는 id와 pw가 들어감
     */
    fun login(loginModel: LoginModel, onResponse : (CommonResponse) -> Unit, onFailure: (Throwable) -> Unit) {
        retrofitRemoteDataSource.login(loginModel, onResponse, onFailure)
    }

    /**
     *  회원가입 할때 사용함
     *  UserModel에는 모든 회원정보가 들어감
     */
    fun register(registerModel: RegisterModel, onResponse : (CommonResponse) -> Unit, onFailure: (Throwable) -> Unit) {
        retrofitRemoteDataSource.register(registerModel, onResponse, onFailure)
    }

    /**
     * 정보 가져올 때 사용함
     * 토큰은.. 이건 뭐 언제받는건지.. 하튼 토큰 넣으세요...^^
     * CommonResponse에 data라는 객체가 있는데 여기에 유저정보 들어있습니다.
     */
    fun getUserData(token: String, onResponse : (CommonResponse) -> Unit, onFailure: (Throwable) -> Unit) {
        retrofitRemoteDataSource.getUserData(token, onResponse, onFailure)
    }

    /**
     *  정보 수정할 때 사용함
     *  토큰 넣어주고, 바뀐 유저모델 넣어주시면 반영됩니당
     */
    fun setUserData(token: String, userModel: UserModel, onResponse : (CommonResponse) -> Unit, onFailure: (Throwable) -> Unit) {
        retrofitRemoteDataSource.setUserData(token, userModel, onResponse, onFailure)
    }
}