package com.david0926.mbit.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.david0926.mbit.R
import com.david0926.mbit.data.LoginRequest
import com.david0926.mbit.data.UserModel
import com.david0926.mbit.databinding.ActivityLoginBinding
import com.david0926.mbit.network.auth.AuthManager
import com.david0926.mbit.ui.dialog.LoadingDialog
import com.david0926.mbit.ui.main.MainActivity
import com.david0926.mbit.ui.register.RegisterActivity
import com.david0926.mbit.util.UserCache
import com.google.gson.Gson
import gun0912.tedkeyboardobserver.TedKeyboardObserver
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.lifecycleOwner = this

        val viewModel: LoginActivityViewModel =
            ViewModelProvider(this).get(LoginActivityViewModel::class.java)
        binding.viewModel = viewModel

        TedKeyboardObserver(this).listen {
            if (it) {
                viewModel.errorMsg.value = ""
                scrollLogin.smoothScrollTo(0, scrollLogin.bottom)
            }
        }

        btnLoginLogin.setOnClickListener {
            val dialog = LoadingDialog(this)
            dialog.setMessage("로그인 중...").show()

            val authManager = AuthManager()
            authManager.login(LoginRequest(viewModel.email.value!!, viewModel.pw.value!!),
                onResponse = { it, user ->
                    dialog.cancel()
                    if (it.status != 200) {
                        viewModel.errorMsg.value = it.message
                        return@login
                    }
                    //TODO: add user caching
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                },
                onFailure = {
                    dialog.cancel()
                    viewModel.errorMsg.value = "로그인 요청에 실패했습니다."
                    it.printStackTrace()
                })
        }

        btnLoginRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}