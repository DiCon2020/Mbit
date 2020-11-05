package com.david0926.mbit.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.david0926.mbit.R
import com.david0926.mbit.data.network.LoginRequest
import com.david0926.mbit.databinding.ActivityLoginBinding
import com.david0926.mbit.network.auth.AuthManager
import com.david0926.mbit.ui.dialog.LoadingDialog
import com.david0926.mbit.ui.main.MainActivity
import com.david0926.mbit.ui.register.RegisterActivity
import com.david0926.mbit.util.UserCache
import gun0912.tedkeyboardobserver.TedKeyboardObserver

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.lifecycleOwner = this

        val viewModel = ViewModelProvider(this).get(LoginActivityViewModel::class.java)
        binding.viewModel = viewModel

        TedKeyboardObserver(this).listen {
            if (it) {
                viewModel.errorMsg.value = ""
                binding.scrollLogin.smoothScrollTo(0, binding.scrollLogin.bottom)
            }
        }

        binding.btnLoginLogin.setOnClickListener {
            val dialog = LoadingDialog(this)
            dialog.setMessage("로그인 중...").show()

            val authManager = AuthManager()
            authManager.login(
                LoginRequest(viewModel.email.value!!, viewModel.pw.value!!),
                onResponse = { response, data ->
                    dialog.cancel()
                    if (response.status != 200) {
                        viewModel.errorMsg.value = response.message
                        return@login
                    }
                    UserCache.setUser(this, data)
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                },
                onFailure = {
                    dialog.cancel()
                    viewModel.errorMsg.value = "로그인 요청에 실패했습니다."
                    it.printStackTrace()
                })
        }

        binding.btnLoginRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}