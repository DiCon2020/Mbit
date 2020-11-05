package com.david0926.mbit.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.david0926.mbit.R
import com.david0926.mbit.data.*
import com.david0926.mbit.databinding.ActivityMainBinding
import com.david0926.mbit.network.auth.AuthManager


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

        val auth = AuthManager()
        var token: String = "";
        // 로그인 하는 부분
        auth.login(LoginRequest("admin123@naver.com", "admin123"), { response, data ->
            Log.w("로그인", response.message.toString()) // 로그인 결과 메세지 (만약 실패했으면 뭐 때문에 실패했는지 들어있음)
            Log.w("로그인", response.status.toString())// 로그인 결과 코드 (200... 202... 403... 같은 코드)
            if(response.success)
                Log.w("로그인", response.accessToken.toString())// 액세스 토큰, 실패했으면 Null값
            Log.w("로그인", response.success.toString()) // 로그인 성공했는지

            if(response.success)
                Log.w("로그인(회원정보)", data!!.username)

        }, {

        })

        // -----끝----- //




    }
}
