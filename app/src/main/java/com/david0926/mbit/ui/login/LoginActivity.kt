package com.david0926.mbit.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.david0926.mbit.R
import com.david0926.mbit.data.auth.LoginRequest
import com.david0926.mbit.databinding.ActivityLoginBinding
import com.david0926.mbit.network.auth.AuthManager
import com.david0926.mbit.ui.dialog.LoadingDialog
import com.david0926.mbit.ui.main.MainActivity
import com.david0926.mbit.ui.register.RegisterActivity
import com.david0926.mbit.util.UserCache
import gun0912.tedkeyboardobserver.TedKeyboardObserver

class LoginActivity : AppCompatActivity() {

    lateinit var viewModel: LoginActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this).get(LoginActivityViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.email.observe(this, { checkNextEnabled() })
        viewModel.pw.observe(this, { checkNextEnabled() })

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
                    if (data!!.personalityType.isEmpty()) {
                        val registerIntent = Intent(this, RegisterActivity::class.java)
                        val bundle = Bundle()

                        bundle.putString("token", response.accessToken)
                        bundle.putSerializable("user", data)
                        registerIntent.putExtras(bundle)

                        startActivity(registerIntent)
                        finish()
                        return@login
                    }
                    UserCache.setUser(this, data, response.accessToken)
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

    private fun checkNextEnabled() {
        viewModel.nextEnabled.value =
            android.util.Patterns.EMAIL_ADDRESS.matcher(viewModel.email.value!!).matches()
                    && !(viewModel.pw.value.isNullOrEmpty())
    }
}