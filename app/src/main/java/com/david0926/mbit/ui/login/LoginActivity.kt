package com.david0926.mbit.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.david0926.mbit.R
import com.david0926.mbit.data.LoginRequest
import com.david0926.mbit.databinding.ActivityLoginBinding
import com.david0926.mbit.network.auth.AuthManager
import com.david0926.mbit.ui.dialog.LoadingDialog
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

        btnLoginLogin.setOnClickListener {
            val dialog = LoadingDialog(this)
            dialog.setMessage("로그인 중...")

            val authManager = AuthManager()
            //authManager.login(LoginRequest())
        }

        btnLoginRegister.setOnClickListener{

        }
    }
}