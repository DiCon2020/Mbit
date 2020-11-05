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
import com.david0926.mbit.network.auth.PostManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


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
        auth.login(LoginRequest("admin123@naver.com", "admin123"), {
            Log.w("로그인", it.message.toString()) // 로그인 결과 메세지 (만약 실패했으면 뭐 때문에 실패했는지 들어있음)
            Log.w("로그인", it.status.toString())// 로그인 결과 코드 (200... 202... 403... 같은 코드)
            Log.w("로그인", it.accessToken.toString())// 액세스 토큰, 실패했으면 Null값
            Log.w("로그인", it.success.toString()) // 로그인 성공했는지
            token = it.accessToken.toString()

            //  유저정보 가져오는 부분
            var user: UserModel = Gson().fromJson(Gson().toJson(it.data), UserModel::class.java)

            Log.w("로그인(회원정보)", user.username)

        }, {

        })

        // 가입하는 부분
        auth.register(
            RegisterRequest(
                "admin12@naver.com",
                "admin12",
                "admin",
                2003,
                "ESFJ",
                null,
                null
            ), {
                Log.w("회원가입", it.message) // 회원가입 결과 메세지 (만약 실패했으면 뭐 때문에 실패했는지 들어있음)
                Log.w("회원가입", it.status.toString())// 회원가입 결과 코드 (200... 202... 403... 같은 코드)
                Log.w("회원가입", it.success.toString()) // 회원가입 성공했는지
            }, {

            })

        // 이 아래는 게시글 레트로핏 사용법... //
        val postManager = PostManager()
        // 토큰 가져오는 부분
        auth.login(LoginRequest("admin123@naver.com", "admin123"), {
            Log.w("로그인", it.message.toString()) // 로그인 결과 메세지 (만약 실패했으면 뭐 때문에 실패했는지 들어있음)
            Log.w("로그인", it.status.toString())// 로그인 결과 코드 (200... 202... 403... 같은 코드)
            Log.w("로그인", it.accessToken.toString())// 액세스 토큰, 실패했으면 Null값
            Log.w("로그인", it.success.toString()) // 로그인 성공했는지
            token = it.accessToken.toString()

            //게시글 불러오는 부분
            postManager.getPosts(token, PostGetRequest(0,0,""), {// personalityType에 공백 집어넣으면 전체 게시글 받아옴
                Log.w("게시글", it.message) // 게시글 Get 결과 메세지 (만약 실패했으면 뭐 때문에 실패했는지 들어있음)
                Log.w("게시글", it.status.toString())// 게시글 Get 결과 코드 (200... 202... 403... 같은 코드)
                Log.w("게시글", it.success.toString()) // 게시글 Get 성공했는지

                val Type = object : TypeToken<ArrayList<Post>>() {}.type
                val posts: ArrayList<Post> = Gson().fromJson<ArrayList<Post>>(
                    Gson().toJson(it.data), Type
                )

            }, {

            });


        }, {

        })

        // -----끝----- //




    }
}
