package com.david0926.mbit.ui.register

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.david0926.mbit.R
import com.david0926.mbit.data.UserModel
import com.david0926.mbit.databinding.ActivityRegisterBinding
import com.david0926.mbit.ui.login.LoginActivity
import gun0912.tedkeyboardobserver.TedKeyboardObserver

class RegisterActivity : AppCompatActivity() {

    lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityRegisterBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_register)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.fragments.addAll(
            listOf(
                Register1Fragment(),
                Register2Fragment(),
                Register3Fragment()
            )
        )

        if (intent.hasExtra("token") && intent.hasExtra("user")) {
            val bundle = intent.extras
            val token = bundle!!.getString("token")
            val user = bundle.getSerializable("user") as UserModel

            viewModel.token.value = token
            viewModel.user.value = user
            viewModel.page.value = 2
        }

        TedKeyboardObserver(this).listen {
            if (it) {
                viewModel.errorMsg.value = ""
                binding.scrollRegister.smoothScrollTo(0, binding.scrollRegister.bottom)
            }
        }

        binding.btnRegisterBack.setOnClickListener { onBackPressed() }
    }

    override fun onBackPressed() {
        if (viewModel.page.value != 0 && viewModel.page.value != 2) viewModel.previousPage()
        else {
            startActivity(Intent(this, LoginActivity::class.java))
            super.onBackPressed()
        }
    }
}