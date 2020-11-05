package com.david0926.mbit.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.david0926.mbit.R
import com.david0926.mbit.databinding.ActivityMainBinding
import com.david0926.mbit.ui.Retrofit.Auth.AuthManager
import com.david0926.mbit.ui.Retrofit.Model.LoginModel
import com.david0926.mbit.ui.Retrofit.Model.RegisterModel
import com.david0926.mbit.ui.Retrofit.Model.UserModel
import com.google.gson.Gson


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        val viewModel: MainActivityViewModel =
            ViewModelProvider(this).get(MainActivityViewModel::class.java)
        binding.viewModel = viewModel


        // ---이 아래는 로그인 레트로핏 사용법.. 확인했으면 지워...--- //

        var auth = AuthManager()
        // 로그인 하는 부분
        auth.login(LoginModel("admin12@naver.com", "admin12"), {
            Log.w("로그인",it.message.toString()) // 로그인 결과 메세지 (만약 실패했으면 뭐 때문에 실패했는지 들어있음)
            Log.w("로그인",it.status.toString())// 로그인 결과 코드 (200... 202... 403... 같은 코드)
            Log.w("로그인",it.accessToken.toString())// 액세스 토큰, 실패했으면 Null값
            Log.w("로그인",it.success.toString()) // 로그인 성공했는지

            //  유저정보 가져오는 부분
            auth.getUserData(it.accessToken, { userdata ->
                Log.w("회원 정보",it.message ) // 유저정보 Get 결과 메세지 (만약 실패했으면 뭐 때문에 실패했는지 들어있음)
                Log.w("회원 정보",it.status.toString()  )// 유저정보 Get 결과 코드 (200... 202... 403... 같은 코드)
                Log.w("회원 정보",it.success.toString() ) // 유저정보 Get 성공했는지

                Log.w("회원 정보", userdata.data.toString())
                Log.w("회원 정보", Gson().toJson(userdata.data))
                val user: UserModel = Gson().fromJson(Gson().toJson(userdata.data), UserModel::class.java) // 받은 유저 정보, 실패했으면 Null값
                Log.w("회원 정보", user.username)
                // data타입 Any로 해둔건 앞으로 어떻게 될지 몰라서 나중에 고쳐드려요
                // 지금 되게 과정이 난잡해 보이는데 해결하면 따로 또 표시할게유

            }, {

            })

        }, {

        })

        // 가입하는 부분
        auth.register(RegisterModel("admin12@naver.com", "admin12", "admin", 2003, "ESFJ", null, null), {
            Log.w("회원가입",it.message ) // 회원가입 결과 메세지 (만약 실패했으면 뭐 때문에 실패했는지 들어있음)
            Log.w("회원가입",it.status.toString()  )// 회원가입 결과 코드 (200... 202... 403... 같은 코드)
            Log.w("회원가입",it.success.toString() ) // 회원가입 성공했는지
        }, {

        })

    // -----끝----- //

    }
}
